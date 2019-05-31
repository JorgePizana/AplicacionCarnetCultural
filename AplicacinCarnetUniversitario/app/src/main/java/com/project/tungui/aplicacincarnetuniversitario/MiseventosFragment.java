package com.project.tungui.aplicacincarnetuniversitario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MiseventosFragment extends Fragment {

    private ExpandableListView expLV;
    private ExpLVAdapter adapter;
    private ArrayList<String> listCategorias;
    private Map<String, ArrayList<String>> mapChild;
    private TextView tv_mise2;

    ConexionSQLiteHelper conn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_miseventos, container, false);

        conn = new ConexionSQLiteHelper(this.getContext());

        tv_mise2 = (TextView) view.findViewById(R.id.tv_mise2);
        tv_mise2.setText(conn.consultarAlumno(InicioActivity.matricula_id));

        conn.seeContent();

        expLV = (ExpandableListView) view.findViewById(R.id.explEventos);
        listCategorias = new ArrayList<>();
        mapChild = new HashMap<>();

        cargarDatos();

        return view;
    }

    private void cargarDatos() {

        ArrayList<String> artisticos = new ArrayList<>();
        ArrayList<String> cientificos = new ArrayList<>();
        ArrayList<String> deportivos = new ArrayList<>();

        listCategorias.add("     Artísticos");
        listCategorias.add("     Científicos");
        listCategorias.add("     Deportivos");

        artisticos.add("Catedral de Chihuahua");
        artisticos.add("Quinta Gameros");
        artisticos.add("Mural de Ingeniería");
        artisticos.add("Exposición 40 Casas");

        cientificos.add("Conferencia Inteligencia Artificial");
        cientificos.add("Palacio de Gobierno");
        cientificos.add("Casa Chihuahua");

        deportivos.add("Ajedrez 1");
        deportivos.add("Parque el Rejón");

        mapChild.put(listCategorias.get(0), artisticos);
        mapChild.put(listCategorias.get(1), cientificos);
        mapChild.put(listCategorias.get(2), deportivos);

        adapter = new ExpLVAdapter(listCategorias, mapChild, this.getContext());
        expLV.setAdapter(adapter);
    }
}
