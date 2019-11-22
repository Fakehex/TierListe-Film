package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.Models.TiersListe;
import com.example.tierliste3.R;

import java.util.ArrayList;
import java.util.HashMap;

public class NouvelleTierListeActivity extends AppCompatActivity {


    ArrayList<Film> mPopularList;



    ArrayList<TiersListe> tiersListeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mPopularList = (ArrayList<Film>) intent.getSerializableExtra("mPopularList");
        tiersListeList = (ArrayList<TiersListe>) intent.getSerializableExtra("tiersListeList");
        setContentView(R.layout.activity_nouvelle_tier_liste);
    }
   /* public void creationTierListe(View view) {
        Intent intent = new Intent(this, TierListeActivity.class);
        EditText editText = (EditText) findViewById(R.id.titre_nouvelle_tier_liste);
        String titre = editText.getText().toString();
        intent.putExtra("mPopularList", mPopularList);
        intent.putExtra("titre", titre);
        startActivity(intent);
    }*/



    public ArrayList<Film> getmPopularList() {
        return mPopularList;
    }
    public ArrayList<TiersListe> getTiersListeHashMap() {
        return tiersListeList;
    }

}
