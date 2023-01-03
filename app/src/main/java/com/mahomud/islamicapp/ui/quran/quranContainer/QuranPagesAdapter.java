package com.mahomud.islamicapp.ui.quran.quranContainer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mahomud.islamicapp.ui.quran.quranPage.QuranPageFragment;

public class QuranPagesAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 604;

    public QuranPagesAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public Fragment createFragment(int position) {
        return new QuranPageFragment(NUM_PAGES - position + 1);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}