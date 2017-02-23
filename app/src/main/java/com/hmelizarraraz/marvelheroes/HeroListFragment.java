package com.hmelizarraraz.marvelheroes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hmelizarraraz.marvelheroes.Models.SuperHero;

import java.util.ArrayList;


public class HeroListFragment extends Fragment {

    private static final String TAG = HeroListFragment.class.getSimpleName();

    ArrayList<SuperHero> superHeros;
    RecyclerView recyclerView;

    public HeroListFragment() {
        // Required empty public constructor
    }

    public interface HeroClickListener {
        void onHeroClicked(SuperHero superHero);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        superHeros = bundle.getParcelableArrayList(MainActivity.HERO_LIST);

        if (superHeros == null)
            Log.d(TAG, "No se recuperaron heroes en el bundle");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hero_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvSuperHeroes);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        HeroAdapter heroAdapter = new HeroAdapter(superHeros, getContext(), new HeroClickListener() {
            @Override
            public void onHeroClicked(SuperHero superHero) {
                // Cambiar de fragment a HeroDetailFragment
                goToHeroDetailFragment(superHero);
            }
        });

        recyclerView.setAdapter(heroAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void goToHeroDetailFragment(SuperHero superHero) {

        Toast.makeText(getContext(), "Hero Clicked: " + superHero.getName(), Toast.LENGTH_SHORT).show();
    }

}
