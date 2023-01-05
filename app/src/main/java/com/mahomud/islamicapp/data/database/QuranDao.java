package com.mahomud.islamicapp.data.database;


import androidx.room.Dao;
import androidx.room.Query;

import com.mahomud.islamicapp.data.pojo.Aya;
import com.mahomud.islamicapp.data.pojo.Sora;

import java.util.List;

@Dao
public interface QuranDao {

    @Query("SELECT * FROM quran WHERE page = :page")
    List<Aya> getAyaByPage(int page);

    @Query("SELECT sora as soraNumber, MIN(page) as startPage, MAX(page) as endPage, sora_name_ar as arabicName, sora_name_en as englishName FROM quran WHERE sora = :soraNumber")
    Sora getSoraByNumber(int soraNumber);
}
