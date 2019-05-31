package com.project.tungui.aplicacincarnetuniversitario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoArtistico;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoCientifico;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoDeportivo;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventosCarnet;
import com.project.tungui.aplicacincarnetuniversitario.RecyclerView.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class EmomentaneosFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adaptadorEvento;
    private List<EventosCarnet> mis_eventos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_emomentaneos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_eventosm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mis_eventos = new ArrayList<>();
        mis_eventos = obtenerEventos();

        adaptadorEvento = new RecyclerViewAdapter(mis_eventos);
        recyclerView.setAdapter(adaptadorEvento);

        adaptadorEvento.setOnItemCLickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {

                Intent i = new Intent(getContext(), InfoEventoActivity.class);

                i.putExtra("titulo", mis_eventos.get(position).getNombreEvento());
                i.putExtra("tipo", mis_eventos.get(position).getTipo());
                i.putExtra("fecha", mis_eventos.get(position).getFecha());
                i.putExtra("lugar", mis_eventos.get(position).getLugar());
                i.putExtra("descripcion", mis_eventos.get(position).getDescripcion());
                i.putExtra("imagen", mis_eventos.get(position).getImgEvento());

                startActivity(i);
            }
        });

        return view;
    }

    public List<EventosCarnet> obtenerEventos() {

        List<EventosCarnet> evento = new ArrayList<>();
        evento.add(new EventoDeportivo("Interfacultades", "28 de Mayo", "Estadio Olímpico Universitario", "Evidencia: Selfie del evento y marcador final", "Deportivo", R.drawable.interfacultades));
        evento.add(new EventoCientifico("Magistral", "23 de Mayo", "FING: Aguilera Baca", "Evidencia: Lista de asistencia", "Científico", R.drawable.magistral));
        evento.add(new EventoCientifico("Taller Python", "24 de Mayo", "FING: Laboratorios LC4", "Evidencia: Lista de asistencia y fotos", "Científico", R.drawable.python));
        evento.add(new EventoArtistico("Poder y Responsabilidad", "24 de Mayo", "FING: Aguilera Baca", "Evidencia: Lista de asistencia", "Artístico", R.drawable.poderyresp));
        evento.add(new EventoCientifico("Intercambio Canadá", "24 de Mayo", "FING: Sala de seminarios", "Evidencia: Lista de asistencia", "Científico", R.drawable.canada));

        return evento;
    }

}
