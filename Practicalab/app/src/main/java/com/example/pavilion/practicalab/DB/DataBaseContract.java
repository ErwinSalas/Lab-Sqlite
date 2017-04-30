package com.example.pavilion.practicalab.DB;

import android.provider.BaseColumns;

/**
 * Created by Pavilion on 28/4/2017.
 */
public class DataBaseContract {
    // Implementa la interfaz BaseColumns para heredar campos estandar del marco de Android _ID

    public static class DataBaseEntry implements BaseColumns {

        // Clase Persona

        public static final String TABLE_NAME_ORDEN = "Orden";

        // private String identificacion; Utilizamos DataBaseEntry._ID de BaseColumns

        // private String correo;

        public static final String COLUMN_NAME_CODIGO = "codigoOrden";

        // private String Nombre

        public static final String COLUMN_NAME_CODIGOPLATO = "codigoPlato";

        // private String primerApellido;

        public static final String COLUMN_NAME_FECHA = "fecha";

        // private String segundoApellido;

        public static final String COLUMN_NAME_HORA = "hora";

        // private String telefono;

        public static final String COLUMN_NAME_COMENTARIO = "comentario";

        // private String celular;

        public static final String COLUMN_NAME_LOCALIZACION = "descripcion";

        // private Date fechaNacimiento;



        // Clase Estudiante

        public static final String TABLE_NAME_PLATO = "Plato";

        // private String carnet;

        public static final String COLUMN_NAME_NOMBRE_PLATO = "nombre";

        // private int carreraBase;

        public static final String COLUMN_NAME_DESCRIPCION = "decripcion";

        // private double promedioPonderado;

        public static final String COLUMN_NAME_PRECIO = "precio";


        // Clase Funcionario

        public static final String TABLE_NAME_INSUMO_PLATO = "InsumoPlato";

        // private int unidadBase;

        public static final String COLUMN_NAME_CODIGO_FORANEO_INSUMO = "codigoInsumo";

        // private int puestoBase;

        public static final String COLUMN_NAME_CODIGO_FORANEO_PLATO = "codigoPlato";




        // Clase Funcionario

        public static final String TABLE_NAME_INSUMO = "Insumo";

        // private int unidadBase;

        public static final String COLUMN_NAME_CODIGO_INSUMO = "codigo";

        // private int puestoBase;

        public static final String COLUMN_NAME_NOMBRE_INSUMO = "nombre";

        public static final String COLUMN_NAME_CANTIDAD= "cantidad";

        public static final String COLUMN_NAME_UNIDAD = "unidad";


    }

// Construir las tablas de la base de datos

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATE";
    private static final String TIME_TYPE = " TIME";

    private static final String REAL_TYPE = " REAL";

    private static final String COMMA_SEP = ",";


    // Creacion de tablas Persona, Estudiante, Funcionario

    public static final String SQL_CREATE_ORDEN =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ORDEN + " (" +

                    DataBaseEntry.COLUMN_NAME_CODIGO + INTEGER_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_CODIGOPLATO + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_FECHA + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_HORA + TEXT_TYPE +

                    COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_COMENTARIO + TEXT_TYPE +

                    COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_LOCALIZACION + TEXT_TYPE + COMMA_SEP +
                     "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_CODIGOPLATO + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_PLATO + "(" + DataBaseEntry.COLUMN_NAME_CODIGOPLATO +" )";


    public static final String SQL_DELETE_ORDEN =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ORDEN;


    public static final String SQL_CREATE_PLATO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_PLATO + " (" +

                    DataBaseEntry.COLUMN_NAME_CODIGOPLATO + INTEGER_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_NOMBRE_PLATO + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_DESCRIPCION + INTEGER_TYPE +

                    COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_PRECIO + REAL_TYPE +

                    COMMA_SEP +

                    "))";

    public static final String SQL_DELETE_PLATO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_PLATO;

    public static final String SQL_CREATE_INSUMO_PLATO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INSUMO_PLATO + " (" +

                    DataBaseEntry.COLUMN_NAME_CODIGO_FORANEO_INSUMO + INTEGER_TYPE  +

                    DataBaseEntry.COLUMN_NAME_CODIGO_FORANEO_PLATO + INTEGER_TYPE + COMMA_SEP +
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_CODIGO_FORANEO_INSUMO + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_INSUMO + "(" + DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO +

                    "))"+
                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_CODIGO_FORANEO_PLATO + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_PLATO + "(" + DataBaseEntry.COLUMN_NAME_CODIGOPLATO +

                    "))";


    public static final String SQL_DELETE_INSUMO_PLATO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INSUMO_PLATO;


    public static final String SQL_CREATE_INSUMO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_INSUMO + " (" +

                    DataBaseEntry.COLUMN_NAME_CODIGO_INSUMO + INTEGER_TYPE + "PRIMARY KEY," +

                    DataBaseEntry.COLUMN_NAME_NOMBRE_INSUMO + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_CANTIDAD + INTEGER_TYPE +

                    COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_UNIDAD+ INTEGER_TYPE +

                    COMMA_SEP +

                    "))";

    public static final String SQL_DELETE_INSUMO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_INSUMO_PLATO;
}
