package com.manoj.movie.adapter;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manoj.movie.R;

import java.util.List;

import static com.manoj.movie.api.api.imageurl;

public class viewDataAdapter extends RecyclerView.Adapter<viewDataAdapter.MyViewHolder>{
    private  com.manoj.movie.adapter.onClickRecycleView onClickRecycleView;
    private List<Datalist> Datalist;

    public viewDataAdapter(List<Datalist> alldata, onClickRecycleView onClickRecycleView) {
        this.Datalist = alldata;
        this.onClickRecycleView = onClickRecycleView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list, viewGroup, false);
        return new MyViewHolder(itemView,onClickRecycleView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.names.setText(Datalist.get(i).getTitle());
        myViewHolder.ratingrate.setText(String.valueOf(Datalist.get(i).getRating()));
        Glide.with(myViewHolder.context).load(imageurl+Datalist.get(i).getPoster()).into(myViewHolder.image);

    }



    @Override
    public int getItemCount() {
        return Datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {
        private onClickRecycleView onClickRecycleView;
        TextView names,ratingrate;
        ImageView image;
        Context context;
        public MyViewHolder(@NonNull View itemView, com.manoj.movie.adapter.onClickRecycleView onClickRecycleView) {
            super(itemView);
            this.onClickRecycleView= onClickRecycleView;
            context=itemView.getContext();
            names = itemView.findViewById(R.id.moviename);
            image = itemView.findViewById(R.id.image);
            ratingrate = itemView.findViewById(R.id.ratingrate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickRecycleView.onItemClick(itemView,getAdapterPosition());
        }
    }
}
