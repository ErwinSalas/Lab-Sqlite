package com.example.pavilion.practicalab.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pavilion on 17/3/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    // debemos incrementar la version de la base de datos
    public static final int DATABASE_VERSION = 2;
    // Nombre de la base de datos
    public static final String DATABASE_NAME = "AndroidStorage.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la base de datos de la app
        db.execSQL(DataBaseContract.SQL_CREATE_PLATO);
        db.execSQL(DataBaseContract.SQL_CREATE_INSUMO);
        db.execSQL(DataBaseContract.SQL_CREATE_ORDEN);
        db.execSQL(DataBaseContract.SQL_CREATE_INSUMO_PLATO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseContract.SQL_DELETE_PLATO);
        db.execSQL(DataBaseContract.SQL_DELETE_INSUMO);
        db.execSQL(DataBaseContract.SQL_DELETE_ORDEN);
        db.execSQL(DataBaseContract.SQL_DELETE_INSUMO_PLATO);
    }
}
