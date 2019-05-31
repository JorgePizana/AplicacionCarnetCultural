package com.project.tungui.aplicacincarnetuniversitario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistroEventoActivity extends AppCompatActivity {

    private EditText campo_nombre_evento, campo_fecha, campo_descripcion, campo_tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_evento);

        campo_nombre_evento = (EditText) findViewById(R.id.et_nomb_evento);
        campo_fecha = (EditText) findViewById(R.id.et_fecha_evento);
        campo_descripcion = (EditText) findViewById(R.id.et_desc_evento);
        campo_tipo = (EditText) findViewById(R.id.et_tipo_evento);
    }
}
