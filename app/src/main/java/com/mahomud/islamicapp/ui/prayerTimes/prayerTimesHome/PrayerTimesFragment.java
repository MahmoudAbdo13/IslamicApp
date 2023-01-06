package com.mahomud.islamicapp.ui.prayerTimes.prayerTimesHome;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mahomud.islamicapp.R;
import com.mahomud.islamicapp.data.pojo.PrayerAPIResponse;
import com.mahomud.islamicapp.data.pojo.PrayerTiming;
import com.mahomud.islamicapp.data.pojo.Timings;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PrayerTimesFragment extends Fragment {

    private RecyclerView times;
    private PrayerTimesAdapter adapter;
    private PrayerTimesViewModel viewModel;
    private ProgressBar progressBar;
    private DatePicker datePicker;

    public PrayerTimesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(PrayerTimesViewModel.class);
        return inflater.inflate(R.layout.fragment_prayer_times, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        times = view.findViewById(R.id.prayers_list);
        progressBar = view.findViewById(R.id.children_progressbar);
        datePicker = view.findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), (datePicker1, year, monthOfYear, dayOfMonth) -> {
            viewModel.setPrayerTiming(dayOfMonth,monthOfYear, year);
                });
        adapter = new PrayerTimesAdapter();

        progressBar.setVisibility(View.VISIBLE);
        viewModel.getPrayerTiming().observe(this, prayerTiming -> {
            adapter.setTiming(prayerTiming);
            progressBar.setVisibility(View.GONE);
            times.setAdapter(adapter);
        });

        /*viewModel.getPrayers("27.6421373", "30.6927903", 4, 1, 2023).enqueue(new Callback<PrayerAPIResponse>() {
            @Override
            public void onResponse(Call<PrayerAPIResponse> call, Response<PrayerAPIResponse> response) {
                Timings timings = response.body().getData().get(5)
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
                ArrayList<PrayerTiming> list = viewModel.convertFromTimings(timings);
                adapter.setTiming(list);
                progressBar.setVisibility(View.GONE);
                times.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PrayerAPIResponse> call, Throwable t) {

            }
        });*/
    }
}