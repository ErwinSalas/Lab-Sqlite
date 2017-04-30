package com.example.pavilion.practicalab.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pavilion.practicalab.DB.DataBaseContract;
import com.example.pavilion.practicalab.DB.DataBaseHelper;
import com.example.pavilion.practicalab.DB.UtilDates;

/**
 * Created by Pavilion on 28/4/2017.
 */
public class Insumo {
    private int códigoInsumo;
    private String nombreInsumo;
    private int cantidadInsumo;
    private String unidadMedidaInsumo;

    public Insumo(int códigoInsumo, String nombreInsumo, int cantidadInsumo, String unidadMedidaInsumo) {
        this.códigoInsumo = códigoInsumo;
        this.nombreInsumo = nombreInsumo;
        this.cantidadInsumo = cantidadInsumo;
        this.unidadMedidaInsumo = unidadMedidaInsumo;
    }

    public int getCódigoInsumo() {
        return códigoInsumo;
    }

    public void setCódigoInsumo(int códigoInsumo) {
        this.códigoInsumo = códigoInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public int getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(int cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public String getUnidadMedidaInsumo() {
        return unidadMedidaInsumo;
    }

    public void setUnidadMedidaInsumo(String unidadMedidaInsumo) {
        this.unidadMedidaInsumo = unidadMedidaInsumo;
    }

    public long insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO, getCódigoInsumo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO, getNombreInsumo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CANTIDAD, getCantidadInsumo());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_UNIDAD, getUnidadMedidaInsumo());

        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, null, values);
    }
    // leer una persona desde la base de datos
    public void leer (Context context, String identificacion){
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CANTIDAD,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_UNIDAD
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO + " = ?";
        String[] selectionArgs = {identificacion};
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        // recorrer los resultados y asignarlos a la clase // aca podria implementarse un ciclo si es necesario
        System.out.println(String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            setCantidadInsumo(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CANTIDAD)));
            setCódigoInsumo(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO)));
            setNombreInsumo(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO)));
            setUnidadMedidaInsumo(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_UNIDAD)));


        }
        else {
            return;
        }
    }
    // eliminar una persona desde la base de datos
    public void eliminar (Context context, String identificacion){
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {identificacion};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_INSUMO, selection, selectionArgs);
    }
}
