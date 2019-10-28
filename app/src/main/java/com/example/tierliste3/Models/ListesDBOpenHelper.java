package com.example.tierliste3.Models;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ListesDBOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_LISTES="liste";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITRE="titre";

    public ListesDBOpenHelper(@Nullable Context contexte,@Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(contexte, name, factory, version);
    }

    public ListesDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public ListesDBOpenHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // On passe dans cette méthode lorsque la base est créée. le paramètre db est donc vide, il faut dcréer les table (la table, dans notre cas)
        String requete =
                "create table "+TABLE_LISTES+" ( "+
                        COLUMN_ID + " integer primary key , "+
                        COLUMN_TITRE + " text not null)";

        db.execSQL(requete);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Pas de deuxieme version
    }


}
