package com.example.tierliste3.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tierliste3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //lance une nouvelle activity pour crée une liste
    public void nouvelleTierListe(View view){
        Intent intent = new Intent(this, NouvelleTierListeActivity.class);
        startActivity(intent);
    }

    //lance une nouvelle activity pour crée une liste
    public void consulterFilms(View view){
        Intent intent = new Intent(this, ConsulterActivity.class);
        startActivity(intent);
    }


}
