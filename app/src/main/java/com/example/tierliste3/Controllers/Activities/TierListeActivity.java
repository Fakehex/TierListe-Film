package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.tierliste3.Models.Film;
import com.example.tierliste3.Models.TiersListe;
import com.example.tierliste3.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

// Affiche la tier liste selectionné avec pour chaque tier une Scroll view
// Cette classe est pas du tout propre j'aurais du faire une fonction qui génere la scrollView, mais je fait que des copier coller pour crée une nouvelle scrollView

public class TierListeActivity extends AppCompatActivity {

    ArrayList<Film> mPopularList;
    ArrayList<Film> tierS;
    ArrayList<Film> tierA;
    ArrayList<Film> tierB;
    ArrayList<Film> tierC;
    ArrayList<Film> tierF;

    @BindView(R.id.scrollview_1)
    HorizontalScrollView scrollView_1;

    Film selectedFilm;
    LinearLayout selectedLinearLayout;
    View selectedView;
    String selectedTier;
    String titre;

    ArrayList<TiersListe> tiersListeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tier_liste);

        Intent intent = getIntent();
        String previousActivity = intent.getStringExtra("activity");
        mPopularList = (ArrayList<Film>) intent.getSerializableExtra("mPopularList");
        tierF = (ArrayList<Film>) intent.getSerializableExtra("tierF");
        tierS = (ArrayList<Film>) intent.getSerializableExtra("tierS");
        tierA = (ArrayList<Film>) intent.getSerializableExtra("tierA");
        tierB = (ArrayList<Film>) intent.getSerializableExtra("tierB");
        tierC = (ArrayList<Film>) intent.getSerializableExtra("tierC");
        tiersListeList = ( ArrayList<TiersListe>) intent.getSerializableExtra("tiersListeList");
        titre = (String) intent.getSerializableExtra("titre");

        setTitle(titre);


        //ScrollView 1
        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);

        Button button;
        for(Film film : mPopularList){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout;
                    selectedView = v;
                    selectedTier = "mPopularList";

                }
            });
            linearLayout.addView(button);
        }

        LinearLayout linearLayout1 = findViewById(R.id.rootContainer);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }


        // TIER S SCROLL VIEW
        HorizontalScrollView scrollView_2 = new HorizontalScrollView(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView_2.setLayoutParams(layoutParams);

        final LinearLayout linearLayout_2 = new LinearLayout(this);
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_2.setLayoutParams(linearParams);
        linearLayout_2.setBackgroundColor(Color.GRAY);
        scrollView_2.addView(linearLayout_2);
        scrollView_2.setFillViewport(true);

        button = new Button(this);
        button.setText("S");
        button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.tiershap));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectedFilm != null){
                    Button newButton = new Button(getApplicationContext());
                    newButton.setText(selectedFilm.getOriginalTitle());
                    newButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for(Film film : tierS){
                                if(film.getOriginalTitle() == ((Button)v).getText() ){
                                    selectedFilm = film;
                                    break;
                                }
                            }
                            selectedLinearLayout = linearLayout_2;
                            selectedView = v;
                            selectedTier = "tierS";

                        }
                    });
                    if(selectedTier == "tierS"){
                        tierS.remove(selectedFilm);
                    }
                    if(selectedTier == "tierF"){
                        tierF.remove(selectedFilm);

                    }
                    if(selectedTier == "tierA"){
                        tierA.remove(selectedFilm);

                    }
                    if(selectedTier == "tierB"){
                        tierB.remove(selectedFilm);

                    }
                    if(selectedTier == "tierC"){
                        tierC.remove(selectedFilm);
                    }
                    if(selectedTier == "mPopularList"){
                        mPopularList.remove(selectedFilm);
                    }
                    tierS.add(selectedFilm);
                    selectedLinearLayout.removeView(selectedView);
                    linearLayout_2.addView(newButton);
                    selectedFilm = null;
                    selectedLinearLayout = null;
                    selectedView = null;

                }
            }
        });
        linearLayout_2.addView(button);

        for(Film film : tierS){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout_2;
                    selectedView = v;
                    selectedTier = "tierS";

                }
            });
            linearLayout_2.addView(button);
        }


        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView_2);
        }

        //-------------------
        // TIER A SCROLL VIEW
        scrollView_2 = new HorizontalScrollView(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView_2.setLayoutParams(layoutParams);

        final LinearLayout linearLayout_4 = new LinearLayout(this);
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_4.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_4.setLayoutParams(linearParams);
        linearLayout_4.setBackgroundColor(Color.WHITE);
        scrollView_2.addView(linearLayout_4);
        scrollView_2.setFillViewport(true);


        button = new Button(this);
        button.setText("A");
        button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.tiershap));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectedFilm != null){
                    Button newButton = new Button(getApplicationContext());
                    newButton.setText(selectedFilm.getOriginalTitle());
                    newButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for(Film film : tierA){
                                if(film.getOriginalTitle() == ((Button)v).getText() ){
                                    selectedFilm = film;
                                    break;
                                }
                            }
                            selectedLinearLayout = linearLayout_4;
                            selectedView = v;
                            selectedTier = "tierA";

                        }
                    });
                    if(selectedTier == "tierS"){
                        tierS.remove(selectedFilm);
                    }
                    if(selectedTier == "tierF"){
                        tierF.remove(selectedFilm);

                    }
                    if(selectedTier == "tierA"){
                        tierA.remove(selectedFilm);

                    }
                    if(selectedTier == "tierB"){
                        tierB.remove(selectedFilm);

                    }
                    if(selectedTier == "tierC"){
                        tierC.remove(selectedFilm);
                    }
                    if(selectedTier == "mPopularList"){
                        mPopularList.remove(selectedFilm);
                    }
                    tierA.add(selectedFilm);
                    selectedLinearLayout.removeView(selectedView);
                    linearLayout_4.addView(newButton);
                    selectedFilm = null;
                    selectedLinearLayout = null;
                    selectedView = null;
                }
            }
        });
        linearLayout_4.addView(button);

        for(Film film : tierA){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout_4;
                    selectedView = v;
                    selectedTier = "tierA";

                }
            });
            linearLayout_4.addView(button);
        }

        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView_2);
        }
        //-------------------
        // TIER B SCROLL VIEW
        scrollView_2 = new HorizontalScrollView(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView_2.setLayoutParams(layoutParams);

        final LinearLayout linearLayout_5 = new LinearLayout(this);
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_5.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_5.setLayoutParams(linearParams);
        linearLayout_5.setBackgroundColor(Color.GRAY);
        scrollView_2.addView(linearLayout_5);
        scrollView_2.setFillViewport(true);



        button = new Button(this);
        button.setText("B");
        button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.tiershap));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectedFilm != null){
                    Button newButton = new Button(getApplicationContext());
                    newButton.setText(selectedFilm.getOriginalTitle());
                    newButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for(Film film : tierB){
                                if(film.getOriginalTitle() == ((Button)v).getText() ){
                                    selectedFilm = film;
                                    break;
                                }
                            }
                            selectedLinearLayout = linearLayout_5;
                            selectedView = v;
                            selectedTier = "tierB";

                        }
                    });
                    if(selectedTier == "tierS"){
                        tierS.remove(selectedFilm);
                    }
                    if(selectedTier == "tierF"){
                        tierF.remove(selectedFilm);

                    }
                    if(selectedTier == "tierA"){
                        tierA.remove(selectedFilm);

                    }
                    if(selectedTier == "tierB"){
                        tierB.remove(selectedFilm);

                    }
                    if(selectedTier == "tierC"){
                        tierC.remove(selectedFilm);
                    }
                    if(selectedTier == "mPopularList"){
                        mPopularList.remove(selectedFilm);
                    }
                    tierB.add(selectedFilm);
                    selectedLinearLayout.removeView(selectedView);
                    linearLayout_5.addView(newButton);
                    selectedFilm = null;
                    selectedLinearLayout = null;
                    selectedView = null;
                }
            }
        });
        linearLayout_5.addView(button);

        for(Film film : tierB){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout_5;
                    selectedView = v;
                    selectedTier = "tierB";

                }
            });
            linearLayout_5.addView(button);
        }

        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView_2);
        }


        //-------------------
        // TIER C SCROLL VIEW
        scrollView_2 = new HorizontalScrollView(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView_2.setLayoutParams(layoutParams);

        final LinearLayout linearLayout_6 = new LinearLayout(this);
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_6.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_6.setLayoutParams(linearParams);
        linearLayout_6.setBackgroundColor(Color.WHITE);
        scrollView_2.addView(linearLayout_6);
        scrollView_2.setFillViewport(true);


        button = new Button(this);
        button.setText("C");
        button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.tiershap));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectedFilm != null){
                    Button newButton = new Button(getApplicationContext());
                    newButton.setText(selectedFilm.getOriginalTitle());
                    newButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for(Film film : tierC){
                                if(film.getOriginalTitle() == ((Button)v).getText() ){
                                    selectedFilm = film;
                                    break;
                                }
                            }
                            selectedLinearLayout = linearLayout_6;
                            selectedView = v;
                            selectedTier = "tierC";

                        }
                    });
                    if(selectedTier == "tierS"){
                        tierS.remove(selectedFilm);
                    }
                    if(selectedTier == "tierF"){
                        tierF.remove(selectedFilm);

                    }
                    if(selectedTier == "tierA"){
                        tierA.remove(selectedFilm);

                    }
                    if(selectedTier == "tierB"){
                        tierB.remove(selectedFilm);

                    }
                    if(selectedTier == "tierC"){
                        tierC.remove(selectedFilm);
                    }
                    if(selectedTier == "mPopularList"){
                        mPopularList.remove(selectedFilm);
                    }
                    tierC.add(selectedFilm);
                    selectedLinearLayout.removeView(selectedView);
                    linearLayout_6.addView(newButton);
                    selectedFilm = null;
                    selectedLinearLayout = null;
                    selectedView = null;
                }
            }
        });
        linearLayout_6.addView(button);

        for(Film film : tierC){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout_6;
                    selectedView = v;
                    selectedTier = "tierC";

                }
            });
            linearLayout_6.addView(button);
        }

        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView_2);
        }

        //-------------------
        // TIER F SCROLL VIEW
        scrollView_2 = new HorizontalScrollView(this);
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView_2.setLayoutParams(layoutParams);

        final LinearLayout linearLayout_3 = new LinearLayout(this);
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout_3.setLayoutParams(linearParams);
        linearLayout_3.setBackgroundColor(Color.GRAY);
        scrollView_2.addView(linearLayout_3);
        scrollView_2.setFillViewport(true);


        button = new Button(this);
        button.setText("F");
        button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setTextSize(20);
        button.setTextColor(Color.WHITE);
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.tiershap));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectedFilm != null){
                    Button newButton = new Button(getApplicationContext());
                    newButton.setText(selectedFilm.getOriginalTitle());
                    newButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            for(Film film : tierF){
                                if(film.getOriginalTitle() == ((Button)v).getText() ){
                                    selectedFilm = film;
                                    break;
                                }
                            }
                            selectedLinearLayout = linearLayout_3;
                            selectedView = v;
                            selectedTier = "tierF";

                        }
                    });
                    if(selectedTier == "tierS"){
                        tierS.remove(selectedFilm);
                    }
                    if(selectedTier == "tierF"){
                        tierF.remove(selectedFilm);

                    }
                    if(selectedTier == "tierA"){
                        tierA.remove(selectedFilm);

                    }
                    if(selectedTier == "tierB"){
                        tierB.remove(selectedFilm);

                    }
                    if(selectedTier == "tierC"){
                        tierC.remove(selectedFilm);
                    }
                    if(selectedTier == "mPopularList"){
                        mPopularList.remove(selectedFilm);
                    }
                    tierF.add(selectedFilm);
                    selectedLinearLayout.removeView(selectedView);
                    linearLayout_3.addView(newButton);
                    selectedFilm = null;
                    selectedLinearLayout = null;
                    selectedView = null;
                }
            }
        });
        linearLayout_3.addView(button);

        for(Film film : tierF){
            button = new Button(this);
            button.setText(film.getOriginalTitle());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    selectedFilm = film;
                    selectedLinearLayout = linearLayout_3;
                    selectedView = v;
                    selectedTier = "tierF";

                }
            });
            linearLayout_3.addView(button);
        }

        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView_2);
        }





    }
    private void ajouterScrollView(){ // A faire pour remplacer les copier coller dégeulasse
    }
    public void sauvegardeTierListe(View view) {
        TiersListe tierListe = new TiersListe(titre,mPopularList,tierS,tierF,tierA,tierB,tierC);
        if(tiersListeList == null){
            tiersListeList = new ArrayList<TiersListe>();
        }
        for(TiersListe elem : tiersListeList){

            if(elem.getTitre().equals(titre)){
                tiersListeList.remove(elem);
                break;
            }
        }
        tiersListeList.add(tierListe);


        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        sharedPreferences.edit()
                .putString("tiersListeList",gson.toJson(tiersListeList))
                .apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }




}

