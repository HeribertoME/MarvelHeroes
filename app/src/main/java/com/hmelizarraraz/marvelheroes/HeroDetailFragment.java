package com.hmelizarraraz.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hmelizarraraz.marvelheroes.Models.SuperHero;
import com.squareup.picasso.Picasso;


public class HeroDetailFragment extends Fragment {

    SuperHero superHero;
    private TextView tvHeroName;
    private TextView tvHeroDescription;
    private ImageView ivHeroPicture;

    public HeroDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            superHero = getArguments().getParcelable(HeroListFragment.SUPER_HERO);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_hero_detail, container, false);

        tvHeroName = (TextView) view.findViewById(R.id.tvHeroDetailTitle);
        tvHeroDescription = (TextView) view.findViewById(R.id.tvHeroDetailDescription);
        ivHeroPicture = (ImageView) view.findViewById(R.id.ivHeroDetailThumbnail);

        tvHeroName.setText(superHero.getName());

        if (superHero.getDescription() != null && !superHero.getDescription().isEmpty())
            tvHeroDescription.setText(superHero.getDescription());
        else
            tvHeroDescription.setText(R.string.no_information_message);
        Picasso.with(getContext()).load(superHero.getThumbnail().getFullPath()).into(ivHeroPicture);

        return view;
    }

}
