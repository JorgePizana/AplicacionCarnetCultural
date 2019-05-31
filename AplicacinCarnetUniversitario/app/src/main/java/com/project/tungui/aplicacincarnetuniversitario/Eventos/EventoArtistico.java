package com.project.tungui.aplicacincarnetuniversitario.Eventos;

public class EventoArtistico extends EventosCarnet {

    public EventoArtistico(String nombreEvento, String fecha, String lugar, String descripcion, String tipo, int imgEvento) {
        super(nombreEvento, fecha, lugar, descripcion, tipo, imgEvento);
    }

    public EventoArtistico(String nombreEvento, String fecha, String tipo, int imgEvento) {
        super(nombreEvento, fecha, tipo, imgEvento);
    }
}
