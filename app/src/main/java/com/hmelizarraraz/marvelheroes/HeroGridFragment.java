package com.hmelizarraraz.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmelizarraraz.marvelheroes.Models.SuperHero;

import java.util.ArrayList;


public class HeroGridFragment extends Fragment {

    private static final String TAG = HeroListFragment.class.getSimpleName();
    public static final String HERO_DETAIL_FRAGMENT = "HERO_DETAIL_FRAGMENT";
    public static final String SUPER_HERO = "SUPER_HERO";

    ArrayList<SuperHero> superHeros;
    private RecyclerView recyclerView;

    public HeroGridFragment() {
        // Required empty public constructor
    }

    public interface HeroClickListener {
        void onHeroClicked(SuperHero superHero);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){

            superHeros = getArguments().getParcelableArrayList(MainActivity.HERO_LIST);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hero_grid, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvSuperHeroes);

        HeroGridAdapter heroGridAdapter = new HeroGridAdapter(superHeros, getContext(), new HeroGridFragment.HeroClickListener() {
            @Override
            public void onHeroClicked(SuperHero superHero) {
                // Cambiar de fragment a HeroDetailFragment
                goToHeroDetailFragment(superHero);
            }
        });


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int) (dpWidth/200);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        recyclerView.setAdapter(heroGridAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void goToHeroDetailFragment(SuperHero superHero) {

        HeroDetailFragment heroDetailFragment = new HeroDetailFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelable(SUPER_HERO, superHero);

        heroDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.placeHolder, heroDetailFragment, HERO_DETAIL_FRAGMENT);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

}
