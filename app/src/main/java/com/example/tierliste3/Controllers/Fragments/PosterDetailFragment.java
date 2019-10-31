package com.example.tierliste3.Controllers.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tierliste3.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class PosterDetailFragment extends Fragment {


    public PosterDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poster_detail, container, false);
    }
    public void set_poster(String url) {
        ImageView view = (ImageView) getView().findViewById(R.id.film_poster);
        Picasso.get()
                .load(url)
                .into(view);

    }
    public void set_rating(String rate){
        TextView textView = (TextView) getView().findViewById(R.id.movie_user_rating);
        textView.setText("Note spectateurs : "+ rate);
    }
    public void set_titre(String titre){
        TextView textView = (TextView) getView().findViewById(R.id.film_titre);
        textView.setText(titre);
    }

}
