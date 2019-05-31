package com.project.tungui.aplicacincarnetuniversitario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText txt_matricula, txt_contrasena;
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_matricula = (EditText) findViewById(R.id.txt_matricula);
        txt_contrasena = (EditText) findViewById(R.id.txt_contrasena);

    }

    // Method for button
    public void acceder(View view) {

        String matricula = txt_matricula.getText().toString();
        String contra = txt_contrasena.getText().toString();

        String contrasena = conn.buscarContrasena(matricula);

        if (contra.equals(contrasena)) {
            Intent myIntent = new Intent(this, InicioActivity.class);
            myIntent.putExtra("matricula", matricula);
            startActivity(myIntent);


            Toast.makeText(getApplicationContext(), "Bienvenido " + conn.consultarAlumno(matricula), Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "El Usuario y Contraseña no coinciden", Toast.LENGTH_SHORT).show();
        }

    }

    public void registrarse(View view) {
        Intent myIntent = new Intent(this, RegistroActivity.class);
        startActivity(myIntent);
    }
}
