package com.mahomud.islamicapp.ui.quran.quranPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahomud.islamicapp.databinding.FragmentPageQuranBinding;


public class QuranPageFragment extends Fragment {

    private FragmentPageQuranBinding binding;
    private QuranViewModel quranViewModel;
    private int pageNumber;

    public QuranPageFragment(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public QuranPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        quranViewModel = new QuranViewModel();
        binding = FragmentPageQuranBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       int quranPage = quranViewModel.getQuranImagePageNumber(getContext(),pageNumber);
        Log.e("TAG", "onViewCreated: "+quranPage);
        binding.quranPage.setImageResource(quranPage);
    }
}