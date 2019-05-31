package com.project.tungui.aplicacincarnetuniversitario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.speech.tts.UtteranceProgressListener;

import com.project.tungui.aplicacincarnetuniversitario.Entidades.Alumnos;
import com.project.tungui.aplicacincarnetuniversitario.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    private static final int VERSION_BASE_DE_DATOS = 1;
    private static final String NOMBRE_BASE_DE_DATOS = "bd_carnet";

    SQLiteDatabase db;

    public ConexionSQLiteHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_ALUMNOS);
        db.execSQL(Utilidades.CREAR_TABLA_EVENTOS);
        db.execSQL(Utilidades.CREAR_TABLA_ALUM_EVEN);


        this.addEventSetting(db, "Cascada de Basaseachi", "Indefinido", "Basaseachi, Chih",
                "Evidencia: Selfie en lugar del evento", "Deportivo", 6656);

        this.addStudentSetting(db, "310905", "Erick", "Villarreal Esquivel",
                "a310905@uach.mx", "maineim");

        this.addStEvSetting(db, "310905", "Cascada de Basaseachi");

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_ALUMNOS);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_EVENTOS);
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_ALUM_EVEN);
        this.onCreate(db);
    }

    public void registrarAlumnosSql(Alumnos alumno) {
        db = this.getWritableDatabase();

        String insert = "INSERT INTO " + Utilidades.TABLA_ALUMNOS + "("
                + Utilidades.CAMPO_MATRICULA + "," + Utilidades.CAMPO_NOMBRES + ","
                + Utilidades.CAMPO_APELLIDOS + "," + Utilidades.CAMPO_CORREO + "," + Utilidades.CAMPO_CONTRASENA + ")"
                + " VALUES (" + alumno.getMatricula() + ", '" + alumno.getNombres()
                + "', '" + alumno.getApellidos() + "', 'a" + alumno.getCorreo() +
                "@uach.mx'" + ", '" + alumno.getContrasena() + "')";

        db.execSQL(insert);
        //db.close();
    }

    public String buscarContrasena(String matricula) {

        db = this.getReadableDatabase();
        String query = "SELECT " + Utilidades.CAMPO_MATRICULA + ", " + Utilidades.CAMPO_CONTRASENA + " from " + Utilidades.TABLA_ALUMNOS;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "no encontrado";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(matricula)) {
                    b = cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }

        return b;
    }

    public String consultarAlumno(String matricula) {
        db = this.getReadableDatabase();
        String[] campos = {Utilidades.CAMPO_NOMBRES, Utilidades.CAMPO_APELLIDOS};
        Cursor cursor = db.query(Utilidades.TABLA_ALUMNOS, campos, Utilidades.CAMPO_MATRICULA + "=?", new String[]{matricula}, null, null, null);

        cursor.moveToFirst();

        return cursor.getString(0) + " " + cursor.getString(1);
    }

    public void addEventSetting(SQLiteDatabase db, String nombre_evento, String fecha, String lugar, String descripcion, String tipo, int imagen) {
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_EVENTO, nombre_evento);
        values.put(Utilidades.CAMPO_FECHA, fecha);
        values.put(Utilidades.CAMPO_LUGAR, lugar);
        values.put(Utilidades.CAMPO_DESCRIPCION, descripcion);
        values.put(Utilidades.CAMPO_TIPO, tipo);
        values.put(Utilidades.CAMPO_IMAGEN, imagen);

        db.insert(Utilidades.TABLA_EVENTOS, null, values);
    }

    public void addStudentSetting(SQLiteDatabase db, String matricula, String nombres, String apellidos, String correo, String contrasena) {
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_MATRICULA, matricula);
        values.put(Utilidades.CAMPO_NOMBRES, nombres);
        values.put(Utilidades.CAMPO_APELLIDOS, apellidos);
        values.put(Utilidades.CAMPO_CORREO, correo);
        values.put(Utilidades.CAMPO_CONTRASENA, contrasena);

        db.insert(Utilidades.TABLA_ALUMNOS, null, values);
    }

    public void addStEvSetting(SQLiteDatabase db, String matricula, String nombre_evento) {
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_MATRICULA, matricula);
        values.put(Utilidades.CAMPO_NOMBRE_EVENTO, nombre_evento);

        db.insert(Utilidades.TABLA_ALUM_EVEN, null, values);
    }

    public void seeContent() {
        String query = "SELECT * FROM alumnos";
        Cursor cursor = db.rawQuery(query, null);

        String a;
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                System.out.println(a);

            }
            while (cursor.moveToNext());
        }

    }
}
