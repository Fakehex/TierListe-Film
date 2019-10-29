package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tierliste3.MyTiersListeRecyclerViewAdapter;
import com.example.tierliste3.R;

public class ListeTiersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_tiers);
        recyclerView = (RecyclerView) findViewById(R.id.listRV);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //mAdapter = new MyTiersListeRecyclerViewAdapter(dataSet);
        recyclerView.setAdapter(mAdapter);

    }
}
