package com.mahomud.islamicapp.data;

import android.content.Context;

import java.text.DecimalFormat;

public class PagesManager {
    public static int getQuranImagePageNumber(Context context, int pageNumber){
        DecimalFormat formatter = new DecimalFormat("000");
        String drawableName = "page"+formatter.format(pageNumber);
        return context.getResources().getIdentifier(drawableName,
                "drawable", context.getPackageName());
    }
}
