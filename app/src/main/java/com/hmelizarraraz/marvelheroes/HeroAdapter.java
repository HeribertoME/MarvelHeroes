package com.hmelizarraraz.marvelheroes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmelizarraraz.marvelheroes.Models.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by heriberto on 16/02/17.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.MyViewHolder>{

    ArrayList<SuperHero> superHeroArrayList;
    Context context;

    public HeroAdapter(ArrayList superHeroArrayList, Context context) {
        this.superHeroArrayList = superHeroArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SuperHero superHero = superHeroArrayList.get(position);

        holder.tvHeroDetailName.setText(superHero.getName());
        //holder.ivHeroPicture.setImageResource(superHero.getThumbnail());
        Picasso.with(context).load(superHero.getThumbnail().getFullPath()).into(holder.ivHeroPicture);
    }

    @Override
    public int getItemCount() {
        return superHeroArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivHeroPicture;
        public TextView tvHeroDetailName;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivHeroPicture = (ImageView) itemView.findViewById(R.id.ivHeroPicture);
            tvHeroDetailName = (TextView) itemView.findViewById(R.id.tvHeroDetailName);
        }
    }
}
