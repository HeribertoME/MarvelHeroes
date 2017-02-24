package com.hmelizarraraz.marvelheroes;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hmelizarraraz.marvelheroes.API.MarvelService;
import com.hmelizarraraz.marvelheroes.Models.Basic;
import com.hmelizarraraz.marvelheroes.Models.Data;
import com.hmelizarraraz.marvelheroes.Models.SuperHero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String HERO_LIST_FRAGMENT = "hero_list_fragment";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int SUCCESS_CODE = 200;
    public static final String HERO_LIST = "hero_list";

    private FrameLayout frameLayout;
    private ArrayList<SuperHero> superHeros;

    public static final int AVENGERS_COMIC_ID = 354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.placeHolder);

        getHeroList();

    }

    private void getHeroList() {

        Call<Basic<Data<ArrayList<SuperHero>>>> superHeroesCall = MarvelService.getMarvelAPI().getHeroes(AVENGERS_COMIC_ID);

        superHeroesCall.enqueue(new Callback<Basic<Data<ArrayList<SuperHero>>>>() {
            @Override
            public void onResponse(Call<Basic<Data<ArrayList<SuperHero>>>> call, Response<Basic<Data<ArrayList<SuperHero>>>> response) {

                if (response.code() == SUCCESS_CODE) {

                    superHeros = response.body().getData().getResults();

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(HERO_LIST, superHeros);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    HeroListFragment savedFragment = (HeroListFragment) fragmentManager.findFragmentByTag(HERO_LIST_FRAGMENT);

                    if (savedFragment == null) {

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        HeroListFragment heroListFragment = new HeroListFragment();
                        heroListFragment.setArguments(bundle);
                        fragmentTransaction.add(R.id.placeHolder, heroListFragment, HERO_LIST_FRAGMENT);
                        fragmentTransaction.commit();
                    }


                } else {

                    displayErrorMesage(getString(R.string.service_error_message));
                }

            }

            @Override
            public void onFailure(Call<Basic<Data<ArrayList<SuperHero>>>> call, Throwable t) {

                displayErrorMesage(getString(R.string.network_error_message));
            }
        });

    }

    public void displayErrorMesage(String mensaje){

        Snackbar snackbar = Snackbar.make(frameLayout, mensaje, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.OK), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getHeroList();
                    }
                });

        snackbar.show();

    }
}
