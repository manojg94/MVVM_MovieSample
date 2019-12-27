package com.manoj.movie.repos;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.manoj.movie.MainActivity;
import com.manoj.movie.adapter.Datalist;
import com.manoj.movie.api.api;
import com.manoj.movie.api.pojo.movielist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    List<Datalist> alldata = new ArrayList<>();
MutableLiveData<List<Datalist>> mutableLiveData=new MutableLiveData<>();
    private static MovieRepository movieRepository;
 // Application application;

//    public MovieRepository(Application application) {
//        this.application = application;
//    }

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<List<Datalist>> getMutableLiveData(int pagenum) {
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(api.baseurl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        api retrfit = retro.create(api.class);
        Call<movielist> call;
        call = retrfit.getmovies(
                "5a9e972c916d99006f4d6ec3c46829ce",
                pagenum);

        call.enqueue(new Callback<movielist>() {
            @Override
            public void onResponse(Call<movielist> call, retrofit2.Response<movielist> response) {

                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                  //      Toast.makeText(, "SuccessFully ...", Toast.LENGTH_SHORT).show();
                        Log.d("results", response.body().getResults().get(i).getOriginalTitle());

                        Datalist datalist = new Datalist(
                                response.body().getResults().get(i).getTitle(),
                                response.body().getResults().get(i).getPosterPath(),
                                response.body().getResults().get(i).getVoteAverage(),
                                response.body().getResults().get(i).getId()
                        );
                        alldata.add(datalist);
                    }

                    mutableLiveData.setValue(alldata);

                } else {

                 //   Toast.makeText(application, "Failed ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<movielist> call, Throwable t) {

                Log.d("errorin:", t.getMessage());
             //   Toast.makeText(application, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        return mutableLiveData;
    }
}
