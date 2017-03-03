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
 * Created by heriberto on 03/03/17.
 */

public class HeroGridAdapter extends RecyclerView.Adapter<HeroGridAdapter.MyViewHolder>{

    ArrayList<SuperHero> superHeroArrayList;
    Context context;
    HeroGridFragment.HeroClickListener heroClickListener;

    public HeroGridAdapter(ArrayList superHeroArrayList, Context context, HeroGridFragment.HeroClickListener heroClickListener) {
        this.superHeroArrayList = superHeroArrayList;
        this.context = context;
        this.heroClickListener = heroClickListener;
    }

    @Override
    public HeroGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hero_list_item, parent, false);
        return new HeroGridAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroGridAdapter.MyViewHolder holder, int position) {
        SuperHero superHero = superHeroArrayList.get(position);

        holder.bind(context, superHero, heroClickListener);
    }

    @Override
    public int getItemCount() {
        return superHeroArrayList.size();
    }

    static public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivHeroPicture;
        public TextView tvHeroDetailName;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivHeroPicture = (ImageView) itemView.findViewById(R.id.ivHeroPicture);
            tvHeroDetailName = (TextView) itemView.findViewById(R.id.tvHeroDetailName);
        }

        public void bind(Context context, final SuperHero superHero, final HeroGridFragment.HeroClickListener heroClickListener) {

            tvHeroDetailName.setText(superHero.getName());
            Picasso.with(context).load(superHero.getThumbnail().getFullPath()).into(ivHeroPicture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    heroClickListener.onHeroClicked(superHero);
                }
            });
        }

    }
}
