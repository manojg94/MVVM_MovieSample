package com.manoj.movie;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.manoj.movie.adapter.Datalist;
import com.manoj.movie.adapter.onClickRecycleView;
import com.manoj.movie.adapter.viewDataAdapter;
import com.manoj.movie.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onClickRecycleView {
    viewDataAdapter adapter;
    RecyclerView recyclerView;
    List<Datalist> alldata = new ArrayList<>();
    int pagenumber;
    String movieCategory;
private MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        movieCategory = "Now Playing";
        pagenumber = 1;
        viewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init(pagenumber);
        adapter = new viewDataAdapter(alldata, MainActivity.this);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        movielistList();

    }



    private void movielistList() {
        clear();
        viewModel.getAllmovies().observe(this, new Observer<List<Datalist>>() {
            @Override
            public void onChanged(List<Datalist> datalists) {
                for (int i=0;i<datalists.size();i++){
                    Datalist datalist = new Datalist(
                            datalists.get(i).getTitle(),
                            datalists.get(i).getPoster(),
                            datalists.get(i).getRating(),
                            datalists.get(i).getId()
                    );
                    alldata.add(datalist);
                    adapter.notifyDataSetChanged();

                }
            }
        });


    }


    public void clear() {
        alldata.clear();
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, alldata.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MovieDetails.class);
        intent.putExtra("movieid", alldata.get(position).getId());
        startActivity(intent);

    }


}
