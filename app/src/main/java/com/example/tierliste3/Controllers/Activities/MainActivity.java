package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.Models.TiersListe;
import com.example.tierliste3.R;
import com.example.tierliste3.utilities.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    String myApiKey = "f98de7b132d6ea35fc6ffe4fbecba252";

    String popularMoviesURL;
    String topRatedMoviesURL;

    ArrayList<Film> mPopularList;
    ArrayList<Film> mTopTopRatedList;

    ArrayList<TiersListe> tiersListeList;


    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Recuperer les tiersListe sauvegardé
        Context context = this.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("tiersListeList",null);

        if(json != null) {
            tiersListeList = gson.fromJson(json, new TypeToken<ArrayList<TiersListe>>() {
            }.getType());

        }else{
            tiersListeList = null;
        }

        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);
        new FetchMovies().execute();
    }
    //lance une nouvelle activity pour crée une liste
    public void nouvelleTierListe(View view){
        Intent intent = new Intent(this, NouvelleTierListeActivity.class);
        intent.putExtra("mPopularList", mPopularList);
        intent.putExtra("tiersListeList", tiersListeList);
        startActivity(intent);
    }

    //lance une nouvelle activity pour crée une liste
    public void consulterFilms(View view){
        Intent intent = new Intent(this, ConsulterActivity.class);
        intent.putExtra("mPopularList", mPopularList);
        intent.putExtra("mTopTopRatedList", mTopTopRatedList);
        startActivity(intent);
    }
    //Consulte les Tierslistes sauvegardé
    public void mesTierListes(View view){
        Intent intent = new Intent(this, MesListesActivity.class);
        intent.putExtra("tiersListeList", tiersListeList);
        startActivity(intent);
    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void, Void, Void> {

        public final static String POPULAR = "popular";
        public final static String TOP_RATED = "top_rated";
        public final static String FAVORITES = "favorites";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... voids) {


            popularMoviesURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + myApiKey + "&language=fr-FR";
            topRatedMoviesURL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + myApiKey + "&language=fr-FR";


            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();
            try {
                if (NetworkUtils.networkStatus(MainActivity.this)) {
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL); //Get popular movies
                    mTopTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL); //Get top rated movies

                } else {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

    }


}
