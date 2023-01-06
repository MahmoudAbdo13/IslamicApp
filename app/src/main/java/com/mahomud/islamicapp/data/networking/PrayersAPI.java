package com.mahomud.islamicapp.data.networking;

import com.mahomud.islamicapp.data.pojo.PrayerAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrayersAPI {

    @GET("calendar")
    Call<PrayerAPIResponse> getPrayers(@Query("latitude") String city,
                                       @Query("longitude") String country,
                                       @Query("method") int method,
                                       @Query("month") int month,
                                       @Query("year") int year);
}
