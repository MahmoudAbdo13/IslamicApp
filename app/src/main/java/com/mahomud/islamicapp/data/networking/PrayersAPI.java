package com.mahomud.islamicapp.data.networking;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrayersAPI{
    @GET
    PrayerAPIResponse getPrayers(@Query("city") String city,
                                 @Query("country") String country,
                                 @Query("method") String method,
                                 @Query("month") String month,
                                 @Query("year") String year);
}
