package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tierliste3.Controllers.Fragments.ImageDetailFragment;
import com.example.tierliste3.Controllers.Fragments.OverviewFragment;
import com.example.tierliste3.Controllers.Fragments.PosterDetailFragment;
import com.example.tierliste3.Models.Film;
import com.example.tierliste3.R;

public class FilmDetailActivity extends AppCompatActivity {
    private final String url_img = "https://image.tmdb.org/t/p/w300";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        Film film_intent = (Film) intent.getSerializableExtra("detail");
        ImageDetailFragment fragmentImage = (ImageDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_backdrop);
        fragmentImage.set_backdrop(url_img + film_intent.getBackdropPath());
        PosterDetailFragment fragmentPoster = (PosterDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_poster);
        fragmentPoster.set_poster(film_intent.getPosterPath());
        fragmentPoster.set_rating(film_intent.getVoteAverage());
        fragmentPoster.set_titre(film_intent.getOriginalTitle());

        OverviewFragment fragementOverview = (OverviewFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_overview);
        fragementOverview.set_Overview(film_intent.getOverview());


    }
}
