package com.manoj.movie;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.manoj.movie.api.api;
import com.manoj.movie.api.pojo.moviedetailss;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.manoj.movie.api.api.imageurl;

public class MovieDetails extends AppCompatActivity {
    Integer movieid, votes, Runningtime;
    Double Rating;
    ProgressDialog progressbar;
    String Movietitle, Movieposter, Releasedate, Synopsis;
    ArrayList<String> Genres = new ArrayList<>();
    TextView movietitl,ratingrate,runningtime,release,genre,vot,synopsis;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movietitl = findViewById(R.id.moviename);
        image = findViewById(R.id.image);

        runningtime = findViewById(R.id.runningtime);
        release = findViewById(R.id.release);
        genre = findViewById(R.id.genre);
        vot = findViewById(R.id.votes);
        synopsis = findViewById(R.id.synopsis);

        ratingrate = findViewById(R.id.ratingrate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieid = extras.getInt("movieid");
            Toast.makeText(this, String.valueOf(movieid), Toast.LENGTH_SHORT).show();
            moviedetails(movieid);
        }
    }


    private void moviedetails(int movieids) {
        progressbar = ProgressDialog.show(MovieDetails.this, "",
                "Loading  Please wait...", true);
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(api.baseurl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        String url = "https://api.themoviedb.org/3/movie/" + movieids;
        api retrfit = retro.create(api.class);
        Call<moviedetailss> call;
        call = retrfit.getmovieDetails(
                url,
                "5a9e972c916d99006f4d6ec3c46829ce"
        );


        call.enqueue(new Callback<moviedetailss>() {
            @Override
            public void onResponse(Call<moviedetailss> call, retrofit2.Response<moviedetailss> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(MovieDetails.this, "SuccessFully ...", Toast.LENGTH_SHORT).show();

                    Movietitle = response.body().getTitle();
                    Movieposter = response.body().getPosterPath();
                    Runningtime = response.body().getRuntime();
                    Releasedate = response.body().getReleaseDate();
                    for (int j = 0; j < response.body().getGenres().size(); j++) {
                        Genres.add(response.body().getGenres().get(j).getName());
                    }


                    Rating = response.body().getVoteAverage();
                    votes = response.body().getVoteCount();
                    Synopsis = response.body().getOverview();

                    progressbar.dismiss();

                    movietitl.setText(Movietitle);
                    runningtime.setText("Running Time : "+String.valueOf(Runningtime)+"  Minutes.");
                    release.setText("Release Date : "+String.valueOf(Releasedate));

                    vot.setText("Votes : "+String.valueOf(votes));
                    String gene = null;
                    StringBuilder sb = new StringBuilder();

                    for (int j = 0; j < Genres.size(); j++) {
                        gene = sb.append(Genres.get(j)).append(",").toString();
                    }
                    genre.setText("Genre : "+gene);
                    synopsis.setText("Synopsis : "+Synopsis);

                   ratingrate.setText(String.valueOf(Rating));
                   Glide.with(getApplicationContext()).load(imageurl+Movieposter).into(image);


                }

            }

            @Override
            public void onFailure(Call<moviedetailss> call, Throwable t) {
                progressbar.dismiss();
                Log.d("errorin:", t.getMessage());
                Toast.makeText(MovieDetails.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
