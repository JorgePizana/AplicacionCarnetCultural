package com.project.tungui.aplicacincarnetuniversitario.Eventos;

public class EventosCarnet {

    private String nombreEvento;
    private String fecha;
    private String lugar;
    private String descripcion;
    private String tipo;
    private int imgEvento;

    public EventosCarnet() {
    }

    public EventosCarnet(String nombreEvento, String fecha, String lugar, String descripcion, String tipo, int imgEvento) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imgEvento = imgEvento;
    }

    public EventosCarnet(String nombreEvento, String fecha, String tipo, int imgEvento) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.tipo = tipo;
        this.imgEvento = imgEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImgEvento() {
        return imgEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImgEvento(int imgEvento) {
        this.imgEvento = imgEvento;
    }

    public void setTipo() {
        if (this instanceof EventoArtistico)
            this.tipo = "Artístico";
        else if (this instanceof EventoCientifico) {
            this.tipo = "Científico";
        } else if (this instanceof EventoDeportivo) {
            this.tipo = "Deportivo";
        }
    }
}
