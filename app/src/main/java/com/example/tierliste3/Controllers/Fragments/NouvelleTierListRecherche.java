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
public class NouvelleTierListRecherche extends Fragment {


    public NouvelleTierListRecherche() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View retView = inflater.inflate(R.layout.fragment_nouvelle_tier_list_recherche, container);

        // Get Fragment belonged Activity
        final FragmentActivity fragmentBelongActivity = getActivity();

        if(retView!=null)
        {
            // Button recherche
            Button rechercheButton = (Button)retView.findViewById(R.id.buttonRecherche);
            rechercheButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView infoRechercheTextView = (TextView) retView.findViewById(R.id.infoRecherche);
                    infoRechercheTextView.setText("Nous allons chercher les maitres étalons");
                }
            });
        }

        return retView;
    }

}
