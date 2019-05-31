package com.project.tungui.aplicacincarnetuniversitario;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.tungui.aplicacincarnetuniversitario.Entidades.Alumnos;
import com.project.tungui.aplicacincarnetuniversitario.Utilidades.Utilidades;

public class RegistroActivity extends AppCompatActivity {

    private EditText campoMatricula, campoNombres, campoApellidos, campoContrasena;
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoMatricula = (EditText) findViewById(R.id.et_desc_evento);
        campoNombres = (EditText) findViewById(R.id.et_nomb_evento);
        campoApellidos = (EditText) findViewById(R.id.et_fecha_evento);
        campoContrasena = (EditText) findViewById(R.id.et_tipo_evento);

        registrar = (Button) findViewById(R.id.button_registrar_datos);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (campoMatricula.getText().toString() != null) {

                    Alumnos alumno = new Alumnos(Integer.parseInt(campoMatricula.getText().toString()), campoNombres.getText().toString(),
                            campoApellidos.getText().toString(), "a" + campoMatricula.getText().toString() + "@uach.mx",
                            campoContrasena.getText().toString());
                    conn.registrarAlumnosSql(alumno);

                    Toast.makeText(getApplicationContext(), "Registro Completado Satisfactoriamente", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Favor de llenar todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void registrarAlumnos() {

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_MATRICULA, campoMatricula.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRES, campoNombres.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDOS, campoApellidos.getText().toString());
        values.put(Utilidades.CAMPO_CORREO, "a" + campoMatricula.getText().toString() + "@uach.mx");
        values.put(Utilidades.CAMPO_CONTRASENA, campoContrasena.getText().toString());


        Long idResultante = db.insert(Utilidades.TABLA_ALUMNOS, Utilidades.CAMPO_MATRICULA, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_LONG).show();
        db.close();
    }
}
