package com.project.tungui.aplicacincarnetuniversitario.Entidades;

import android.support.v4.app.INotificationSideChannel;

public class Alumnos {

    private Integer matricula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;

    public Alumnos() {
    }

    public Alumnos(Integer matricula, String nombres, String apellidos, String correo, String contrasena) {
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
