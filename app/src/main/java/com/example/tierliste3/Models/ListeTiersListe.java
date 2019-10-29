package com.example.tierliste3.Models;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListeTiersListe {



    //Base de donnée: COntexte d el'app

    Context appContexte;
    ListesDBOpenHelper dbHelper;
    public static final List<TiersListe> ITEMS = new ArrayList<TiersListe>();
    ArrayList<TiersListe> sListeTiers;

    /**
     * Créé une liste et la charge depuis la BDD localesi besoin
     * @param ctx contexte de l'app
     */
    public ListeTiersListe(Context ctx) {


        sListeTiers = new ArrayList<>();
        appContexte = ctx.getApplicationContext();
        dbHelper = new ListesDBOpenHelper(appContexte,"listedb",null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ListesDBOpenHelper.TABLE_LISTES,new String[]{ListesDBOpenHelper.COLUMN_TITRE},null,null,null,null,null);
        try{
            while(cursor.moveToNext()){
                //sListeTiers.add(new TiersListe(cursor.getString(0),cursor.getInt(1)));
                }
            }

       finally{
            cursor.close();
        }
       db.close();

    }

    public void ajouterTiersliste(String titre){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues toAdd = new ContentValues();
        toAdd.put(ListesDBOpenHelper.COLUMN_TITRE,titre);
    }

    public int size(){
        return sListeTiers.size();
    }

}
