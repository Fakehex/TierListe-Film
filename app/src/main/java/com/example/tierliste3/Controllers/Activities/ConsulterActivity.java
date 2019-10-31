package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.R;
import com.example.tierliste3.utilities.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsulterActivity extends AppCompatActivity implements FilmAdapter.OnItemClickListener{
    private static final String TAG = "ConsulterActivity";

    String myApiKey = "f98de7b132d6ea35fc6ffe4fbecba252";

    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    @BindView(R.id.recycled_movie_grid)
    RecyclerView movie_grid_recyclerView;

    String popularMoviesURL;
    String topRatedMoviesURL;

    ArrayList<Film> mPopularList;
    ArrayList<Film> mTopTopRatedList;

    private FilmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);
        //Define recyclerView Layout
        movie_grid_recyclerView.setLayoutManager(new GridLayoutManager(this, getResources()
                .getInteger(R.integer.number_of_grid_columns)));
        mAdapter = new FilmAdapter(this, new ArrayList<Film>(), ConsulterActivity.this);
        movie_grid_recyclerView.setAdapter(mAdapter);
        new FetchMovies().execute();
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
                if (NetworkUtils.networkStatus(ConsulterActivity.this)) {
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL); //Get popular movies
                    mTopTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL); //Get top rated movies

                } else {
                    Toast.makeText(ConsulterActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
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
            mAdapter = new FilmAdapter(ConsulterActivity.this,new ArrayList<Film>(), ConsulterActivity.this);
            mAdapter.add(mPopularList);
            movie_grid_recyclerView.setAdapter(mAdapter);
        }

    }
    public void send_details(Film movie, int position) {
            Intent intent = new Intent(this, FilmDetailActivity.class);
            intent.putExtra("detail", movie);
            startActivity(intent);
        }
}

