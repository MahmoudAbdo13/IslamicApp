package com.mahomud.islamicapp.ui.quran.quranPage;

import android.content.Context;

import com.mahomud.islamicapp.data.PagesManager;

public class QuranViewModel {

    public int getQuranImagePageNumber(Context context, int pageNumber){
        return PagesManager.getQuranImagePageNumber(context, pageNumber);
    }

}
