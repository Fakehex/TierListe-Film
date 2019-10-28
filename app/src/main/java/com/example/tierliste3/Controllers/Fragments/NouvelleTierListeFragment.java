package com.example.tierliste3.Controllers.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tierliste3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NouvelleTierListeFragment extends Fragment {


    public NouvelleTierListeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_nouvelle_tier_liste, container);

        // Get Fragment belonged Activity
        final FragmentActivity fragmentBelongActivity = getActivity();

        if(retView!=null)
        {
            // Button recherche rang S
            Button rangSButton = (Button)retView.findViewById(R.id.button_rang_s);
            rangSButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = fragmentBelongActivity.getSupportFragmentManager();
                    Fragment rechercheFragment = fragmentManager.findFragmentById(R.id.fragmentRecherche);
                    final TextView rightFragmentTextView = (TextView)rechercheFragment.getView().findViewById(R.id.infoRecherche);
                    rightFragmentTextView.setText("Recherche Rang S");
                    Button buttonRecherche = (Button)rechercheFragment.getView().findViewById(R.id.buttonRecherche);
                    buttonRecherche.setEnabled(true);
                }
            });
            // Button recherche rang trash
            Button rangTrashButton = (Button)retView.findViewById(R.id.button_rang_trash);
            rangTrashButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = fragmentBelongActivity.getSupportFragmentManager();
                    Fragment rechercheFragment = fragmentManager.findFragmentById(R.id.fragmentRecherche);
                    final TextView rightFragmentTextView = (TextView)rechercheFragment.getView().findViewById(R.id.infoRecherche);
                    rightFragmentTextView.setText("Recherche Rang Trash");
                    Button buttonRecherche = (Button)rechercheFragment.getView().findViewById(R.id.buttonRecherche);
                    buttonRecherche.setEnabled(true);
                }
            });
        }

        return retView;
    }

}
