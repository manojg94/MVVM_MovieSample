package com.manoj.movie.viewmodel;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manoj.movie.adapter.Datalist;
import com.manoj.movie.repos.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<List<Datalist>> mutableLiveData;

    public void init(int pagenumber){
        if (mutableLiveData != null){
            return;
        }
        movieRepository = movieRepository.getInstance();
        mutableLiveData = movieRepository.getMutableLiveData(pagenumber);

    }


    public LiveData<List<Datalist>> getAllmovies(){
        return mutableLiveData;
    }
}