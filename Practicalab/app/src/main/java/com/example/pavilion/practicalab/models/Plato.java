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
public class Plato {
    private int codigoPlato;
    private String nombrePlato;
    private String descripción;
    private int precio;

    public Plato(int codigoPlato, String nombrePlato, String descripción, int precio) {
        this.codigoPlato = codigoPlato;
        this.nombrePlato = nombrePlato;
        this.descripción = descripción;
        this.precio = precio;
    }

    public int getCodigoPlato() {
        return codigoPlato;
    }

    public void setCodigoPlato(int codigoPlato) {
        this.codigoPlato = codigoPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public long insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO, getCodigoPlato());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO, getNombrePlato());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION, getDescripción().toString());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PRECIO, getPrecio());
        // Insertar la nueva fila
        return db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, null, values);
    }
    // leer una persona desde la base de datos
    public void leer (Context context, String identificacion){
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_PRECIO
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO + " = ?";
        String[] selectionArgs = {identificacion};
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, // tabla
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
            setCodigoPlato(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO)));
            setNombrePlato(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO)));
            setDescripción(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPCION)));
            setPrecio(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_PRECIO)));

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
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {identificacion};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_PLATO, selection, selectionArgs);
    }

}
