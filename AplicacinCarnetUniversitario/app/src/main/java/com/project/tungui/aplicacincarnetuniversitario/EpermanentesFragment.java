package com.project.tungui.aplicacincarnetuniversitario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoArtistico;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoDeportivo;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventosCarnet;
import com.project.tungui.aplicacincarnetuniversitario.RecyclerView.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class EpermanentesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adaptadorEvento;
    private List<EventosCarnet> mis_eventos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_epermanentes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_eventosp);
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
        evento.add(new EventoArtistico("Catedral de Chihuahua", "Indefinido", "Calle Guadalupe Victoria S/N, Zona Centro, 31000", "Evidencia: Selfie del Evento", "Artístico", R.drawable.catedralchihuahua));
        evento.add(new EventoDeportivo("Presa El Rejón", "Indefinido", "Calle D. Reliz, Av. Teófilo Borunda S/N", "Evidencia: Selfie del Evento", "Deportivo", R.drawable.presa));
        evento.add(new EventoArtistico("Castillo de Chapultepec", "Indefinido", "Bosque de Chapultepec I Secc, 11100 Mexico City, CDMX", "Evidencia: Selfie del Evento", "Artístico", R.drawable.castillo));
        evento.add(new EventoArtistico("Catedral de Guadalajara", "Indefinido", "Av Alcalde 10, Zona Centro, 44100 Guadalajara, Jal", "Evidencia: Selfie del Evento", "Artístico", R.drawable.catguadala));
        evento.add(new EventoArtistico("Estatua de la Libertad", "Indefinido", "New York, NY 10004, USA", "Evidencia: Selfie del Evento","Artístico", R.drawable.libertad));
        evento.add(new EventoArtistico("Torre Eifel", "Indefinido", "Champ de Mars, 5 Avenue Anatole France, 75007", "Evidencia: Selfie del Evento","Artístico", R.drawable.eiffeltower));

        return evento;
    }
}
