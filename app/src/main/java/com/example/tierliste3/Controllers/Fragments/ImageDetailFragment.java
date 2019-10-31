package com.example.tierliste3.Controllers.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tierliste3.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageDetailFragment extends Fragment {


    public ImageDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_detail, container, false);
    }
    public void set_backdrop(String url) {
        ImageView view = (ImageView) getView().findViewById(R.id.film_imagefond);
        Picasso.get()
                .load(url)
                .into(view);
    }

}
