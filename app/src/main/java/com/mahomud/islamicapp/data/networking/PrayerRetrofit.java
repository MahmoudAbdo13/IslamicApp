package com.mahomud.islamicapp.data.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrayerRetrofit {
    private static Retrofit instance;
    //http://api.aladhan.com/v1/calendar?latitude=51.508515&longitude=-0.1254872&method=2&month=4&year=2017
    public static final String BASE_URL = "http://api.aladhan.com/v1/calendarByCity";
    private static PrayersAPI api;
    public static Retrofit getInstance(){
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
    public static PrayersAPI getAPI(){
        if (api == null){
            api = getInstance().create(PrayersAPI.class);
        }
        return api;
    }
}
