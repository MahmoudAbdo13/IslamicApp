package com.mahomud.islamicapp.ui.prayerTimes.prayerTimesHome;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mahomud.islamicapp.data.networking.PrayerRetrofit;
import com.mahomud.islamicapp.data.pojo.PrayerAPIResponse;
import com.mahomud.islamicapp.data.pojo.PrayerTiming;
import com.mahomud.islamicapp.data.pojo.Timings;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTimesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<PrayerTiming>> prayerTiming;

    Call<PrayerAPIResponse> getPrayers(String city, String country,
                                       int method, int month, int year){
        return PrayerRetrofit.getAPI().getPrayers(city, country, method, month, year);
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

    public PrayerTimesViewModel() {
        prayerTiming = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<PrayerTiming>> getPrayerTiming() {
        return prayerTiming;
    }

    public void setPrayerTiming(int day, int month, int year) {
        getPrayers("27.6421373", "30.6927903", 4, month, year).enqueue(new Callback<PrayerAPIResponse>() {
            @Override
            public void onResponse(Call<PrayerAPIResponse> call, Response<PrayerAPIResponse> response) {
                Timings timings = response.body().getData().get(day-1)
                        .getTimings();
                Log.e("TAG", "onResponse: 5 "+response.body().getData().get(5).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 7 "+response.body().getData().get(6).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 8 "+response.body().getData().get(7).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 9 "+response.body().getData().get(8).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 10 "+response.body().getData().get(9).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 11 "+response.body().getData().get(10).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 12 "+response.body().getData().get(11).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 13 "+response.body().getData().get(12).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 14 "+response.body().getData().get(13).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 15 "+response.body().getData().get(14).getTimings().getFajr() );
                Log.e("TAG", "onResponse: 15 "+response.body().getData().get(15).getTimings().getFajr() );
                ArrayList<PrayerTiming> list = convertFromTimings(timings);
                prayerTiming.setValue(list);
            }

            @Override
            public void onFailure(Call<PrayerAPIResponse> call, Throwable t) {
                Log.e("PrayerTimesViewModel", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
