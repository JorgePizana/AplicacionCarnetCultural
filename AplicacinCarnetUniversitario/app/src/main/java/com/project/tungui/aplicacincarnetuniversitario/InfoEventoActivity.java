package com.project.tungui.aplicacincarnetuniversitario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoEventoActivity extends AppCompatActivity {

    private TextView tv_titulo;
    private TextView tv_tipo;
    private TextView tv_fecha;
    private TextView tv_lugar;
    private TextView tv_descripcion;
    private ImageView img_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_evento);

        tv_titulo = (TextView) findViewById(R.id.txt_info_titulo);
        tv_tipo = (TextView) findViewById(R.id.txt_info_tipo);
        tv_fecha = (TextView) findViewById(R.id.txt_info_fecha);
        tv_lugar = (TextView) findViewById(R.id.txt_info_lugar);
        tv_descripcion = (TextView) findViewById(R.id.txt_info_descripcion);
        img_info = (ImageView) findViewById(R.id.img_info);

        String titulo = getIntent().getStringExtra("titulo");
        String tipo = getIntent().getStringExtra("tipo");
        String fecha = getIntent().getStringExtra("fecha");
        String lugar = getIntent().getStringExtra("lugar");
        String descripcion = getIntent().getStringExtra("descripcion");
        int imagen = getIntent().getIntExtra("imagen", 0);

        tv_titulo.setText(titulo);
        tv_tipo.setText(tipo);
        tv_fecha.setText(fecha);
        tv_lugar.setText(lugar);
        tv_descripcion.setText(descripcion);
        img_info.setImageResource(imagen);
    }
}
