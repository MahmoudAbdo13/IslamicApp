package com.mahomud.islamicapp.data.pojo.azan;


import android.content.Context;
import android.icu.util.Calendar;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.mahomud.islamicapp.data.networking.PrayerRetrofit;
import com.mahomud.islamicapp.data.pojo.Datum;
import com.mahomud.islamicapp.data.pojo.PrayerAPIResponse;
import com.mahomud.islamicapp.data.pojo.PrayerTiming;
import com.mahomud.islamicapp.data.pojo.Timings;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public class RegisterPrayerTimesWorker extends Worker {

    public RegisterPrayerTimesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Calendar calendar = Calendar.getInstance();
            PrayersPreferences preferences = new PrayersPreferences(getApplicationContext());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            String city = preferences.getCity();
            String country = preferences.getCountry();
            int method = preferences.getMethod();

            Response<PrayerAPIResponse> timesResponse = PrayerRetrofit.getAPI().getPrayers(city, country, method, month, year).execute();
            if (timesResponse.isSuccessful()) {
                List<Datum> data = timesResponse.body().getData();
                for (int i = 0; i < data.size(); i++) {
                    Datum datum = data.get(i);
                    Timings timings = datum.getTimings();
                    ArrayList<PrayerTiming> prayers = convertFromTimings(timings);
                    int day = i + 1;
                    prayers.forEach(prayerTiming -> {

                        String prayerTag = "" + year + "/" + month + "/" + day + " " + prayerTiming.getPrayerName();
                        long delay = calculatePrayerDelay(year, month, day, prayerTiming);
                        if (delay > 0) {
                            Data input = new Data.Builder()
                                    .putString(AzanNotificationConstants.NOTIFICATION_TITLE_KEY, prayerTiming.getPrayerName())
                                    .putString(AzanNotificationConstants.NOTIFICATION_CONTENT_KEY, "حي على الصلاة")
                                    .build();

                            OneTimeWorkRequest registerPrayerRequest = new OneTimeWorkRequest
                                    .Builder(AzanNotificationWorker.class)
                                    .addTag(prayerTag)
                                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                                    .setInputData(input)
                                    .build();

                            WorkManager.getInstance(getApplicationContext())
                                    .enqueueUniqueWork(prayerTag, ExistingWorkPolicy.REPLACE, registerPrayerRequest);
                        }
                    });
                }
                return Result.success();
            } else {
                return Result.failure();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.retry();
        }
    }

    private long calculatePrayerDelay(int year, int month, int day, PrayerTiming prayerTiming) {
        String pattern = "yyyy/MM/dd HH:mm";
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String time = prayerTiming.getPrayerTime().split(" ")[0];
        String prayerDate = "" + year + "/" + decimalFormat.format(month) + "/" + decimalFormat.format(day) + " " + time;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

        try {
            Date date = format.parse(prayerDate);
            long currentTime = System.currentTimeMillis();
            Log.d("TAG", "calculatePrayerDelay: " + date.toString());
            Log.d("TAG", "calculatePrayerDelay: diff = " + (date.getTime() - currentTime) + " " + date.getTime());
            return (date.getTime() - currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    ArrayList<PrayerTiming> convertFromTimings(Timings timings) {
        ArrayList<PrayerTiming> res = new ArrayList<>();
        res.add(new PrayerTiming("Fajr", timings.getFajr()));
        res.add(new PrayerTiming("Dhuhr", timings.getDhuhr()));
        res.add(new PrayerTiming("Asr", timings.getAsr()));
        res.add(new PrayerTiming("Maghrib", timings.getMaghrib()));
        res.add(new PrayerTiming("Isha", timings.getIsha()));
        return res;
    }
}

