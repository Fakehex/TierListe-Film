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

public class MesListesActivity extends AppCompatActivity {


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

        //ScrollView 1
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);

        Button button;
        if(tiersListeList != null) {
            for (TiersListe elem : tiersListeList) {
                button = new Button(this);
                button.setText(elem.getTitre());
                button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                linearLayout.addView(button);
            }
        }

        LinearLayout linearLayout1 = findViewById(R.id.rootContainerMesListes);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }


    }
}
