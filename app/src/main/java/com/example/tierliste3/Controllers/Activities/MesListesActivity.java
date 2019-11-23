package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.Models.TiersListe;
import com.example.tierliste3.R;

import java.util.ArrayList;

public class MesListesActivity extends AppCompatActivity implements TierListeAdapter.OnItemClickListener{


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<TiersListe> tiersListeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_listes);

        Intent intent = getIntent();
        tiersListeList = (ArrayList<TiersListe>) intent.getSerializableExtra("tiersListeList");
        setTitle("Mes Tier Listes");
        recyclerView = (RecyclerView) findViewById(R.id.meslistes_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TierListeAdapter(tiersListeList, MesListesActivity.this);
        recyclerView.setAdapter(mAdapter);



    }

    public void vers_tierListe(TiersListe tiersListe, int position) {
        Intent intent = new Intent(this, TierListeActivity.class);
        intent.putExtra("mPopularList", tiersListe.getBase());
        intent.putExtra("tierF", tiersListe.getTierF());
        intent.putExtra("tierS", tiersListe.getTierS());
        intent.putExtra("tiersListeList", tiersListeList);
        intent.putExtra("titre", tiersListe.getTitre());
        startActivity(intent);
    }
}
