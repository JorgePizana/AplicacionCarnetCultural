package com.project.tungui.aplicacincarnetuniversitario.Utilidades;

public class Utilidades {

    // Tabla ALUMNOS
    public static final String TABLA_ALUMNOS = "alumnos";
    public static final String CAMPO_MATRICULA = "matricula";
    public static final String CAMPO_NOMBRES = "nombres";
    public static final String CAMPO_APELLIDOS = "apellidos";
    public static final String CAMPO_CORREO = "correo";
    public static final String CAMPO_CONTRASENA = "contrasena";

    public static final String CREAR_TABLA_ALUMNOS = "CREATE TABLE " + TABLA_ALUMNOS + "(" + CAMPO_MATRICULA +
            " INTEGER PRIMARY KEY NOT NULL, " + CAMPO_NOMBRES + " TEXT NOT NULL, " + CAMPO_APELLIDOS + " TEXT NOT NULL, " +
            CAMPO_CORREO + " TEXT, " + CAMPO_CONTRASENA + " TEXT NOT NULL)";

    // ----------------------------------------------------------------------------------------------------------------------

    // Tabla Eventos
    public static final String TABLA_EVENTOS = "eventos";
    public static final String CAMPO_NOMBRE_EVENTO = "nombre_evento";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_LUGAR = "lugar";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_IMAGEN = "imagen";

    public static final String CREAR_TABLA_EVENTOS = "CREATE TABLE " + TABLA_EVENTOS + "(" + CAMPO_NOMBRE_EVENTO +
            " TEXT PRIMARY KEY NOT NULL, " + CAMPO_FECHA + " TEXT, " + CAMPO_LUGAR + " TEXT NOT NULL, " +
            CAMPO_DESCRIPCION + " TEXT, " + CAMPO_TIPO + " TEXT, " + CAMPO_IMAGEN + " INTEGER NOT NULL)";

    // ----------------------------------------------------------------------------------------------------------------------

    // Tabla Alumnos-Eventos
    public static final String TABLA_ALUM_EVEN = "alumnos_eventos";

    public static final String CREAR_TABLA_ALUM_EVEN = "CREATE TABLE " + TABLA_ALUM_EVEN + "(" + CAMPO_MATRICULA +
            " TEXT NOT NULL, " + CAMPO_NOMBRE_EVENTO + " TEXT NOT NULL, " + " PRIMARY KEY (" + CAMPO_MATRICULA + ", " + CAMPO_NOMBRE_EVENTO +
            "), FOREIGN KEY (" + CAMPO_MATRICULA + ") REFERENCES " + TABLA_ALUMNOS + "(" + CAMPO_MATRICULA + "), " +
            "FOREIGN KEY (" + CAMPO_NOMBRE_EVENTO + ") REFERENCES " + TABLA_EVENTOS + "(" + CAMPO_NOMBRE_EVENTO + ")";


}
