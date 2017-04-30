package com.example.pavilion.practicalab.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pavilion.practicalab.DB.DataBaseContract;
import com.example.pavilion.practicalab.DB.DataBaseHelper;
import com.example.pavilion.practicalab.DB.UtilDates;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.pavilion.practicalab.DB.UtilDates.StringToDate;
import static com.example.pavilion.practicalab.DB.UtilDates.StringtoDateSQL;

/**
 * Created by Pavilion on 28/4/2017.
 */
public class Orden {
    private int codigoOrden;
    private int codigoPlato ;
    private Date fecha;
    private String hora;
    private String comentario;
    private String localización;


    public Orden(int codigoOrden,
                 int codigoPlato,
                 String hora,
                 String comentario,
                 String localización) {
        this.codigoOrden = codigoOrden;
        this.codigoPlato = codigoPlato;
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        this.fecha = StringtoDateSQL(formattedDate);
        this.hora = hora;
        this.comentario = comentario;
        this.localización = localización;
    }

    public Orden() {
    }

    public int getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(int codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public int getCodigoPlato() {
        return codigoPlato;
    }

    public void setCodigoPlato(int codigoPlato) {
        this.codigoPlato = codigoPlato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setFecha(String fecha) {

        this.fecha = StringToDate(fecha);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLocalización() {
        return localización;
    }

    public void setLocalización(String localización) {
        this.localización = localización;
    }


    public long insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO, getCodigoOrden());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO, getCodigoPlato());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA, UtilDates.DateToStringShort(getFecha()));
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_HORA, getHora());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO, getComentario());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_LOCALIZACION, getLocalización());
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
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_HORA,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_LOCALIZACION
        };

        // Filtro para el WHERE
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO + " = ?";
        String[] selectionArgs = {identificacion};
        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, // tabla
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
            setCodigoOrden(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO)));
            setCodigoPlato(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGOPLATO)));
            setComentario(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO)));
            setFecha(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_FECHA)));
            setHora(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_HORA)));
            setComentario(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_COMENTARIO)));
            setLocalización(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_LOCALIZACION)));

        }
        else {
            return;
        }
    }
    // eliminar una persona desde la base de datos
    public void eliminar (Context context, int identificacion){
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CODIGO + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {String.valueOf(identificacion)};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN, selection, selectionArgs);
    }
    public ArrayList<Orden> getListData(Context ctx){
        String selectQuery = "SELECT  * FROM " + DataBaseContract.DataBaseEntry.TABLE_NAME_ORDEN;
        DataBaseHelper nuevoHelper = new DataBaseHelper(ctx);
        SQLiteDatabase db = nuevoHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Orden> arrayListData = new ArrayList<Orden>();
        if (cursor.moveToFirst()){
            do {
                Orden orden = new Orden();
                orden.setCodigoOrden(cursor.getInt(0));
                orden.setComentario(cursor.getString(4));
                orden.setFecha(cursor.getString(2));
                orden.setHora(cursor.getString(3));
                orden.setLocalización(cursor.getString(5));
                arrayListData.add(orden);
            } while (cursor.moveToNext());
        }
        return arrayListData;
    }

}
