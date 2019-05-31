package com.project.tungui.aplicacincarnetuniversitario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoArtistico;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoCientifico;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventoDeportivo;
import com.project.tungui.aplicacincarnetuniversitario.Eventos.EventosCarnet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class EnviarFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static Context contextOfApplication;
    private Spinner spinner_rubro, spinner_evento;

    private Button enviar, crear;
    private ImageButton evidencia;
    private EditText comentarios;
    String attachmentFile;

    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_enviar, container, false);


        spinner_rubro = (Spinner) view.findViewById(R.id.spinner_rubro);
        spinner_evento = (Spinner) view.findViewById(R.id.spinner_evento);
        evidencia = (ImageButton) view.findViewById(R.id.evidencia_button);
        enviar = (Button) view.findViewById(R.id.enviar_button);
        comentarios = (EditText) view.findViewById(R.id.etxt_comentario);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evento = spinner_evento.getSelectedItem().toString();
                String comentario = comentarios.getText().toString();

                sendEmail(evento, comentario);
            }
        });

        evidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });



        // ---------------------------------------------------------------------------------------

        String [] opciones_spinner_rubro = {"Artístico", "Científico", "Deportivo"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item_estilo, opciones_spinner_rubro);
        spinner_rubro.setAdapter(adapter1);
        spinner_rubro.setOnItemSelectedListener(this);

        crear = (Button) view.findViewById(R.id.registrare_button);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), RegistroEventoActivity.class);
                startActivity(myIntent);
            }
        });

        return view;
    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getContextOfApplication();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
//        Context applicationContext = EnviarFragment.getContextOfApplication();
//
//        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContext().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            attachmentFile = cursor.getString(columnIndex);
//            Log.e("Attachment Path:", attachmentFile);
//            URI = Uri.parse("file://" + attachmentFile);
//            cursor.close();
//        }

        if (resultCode == RESULT_OK) {
            //check that is the same request code that pass above
            if (requestCode == 1) {
                //get uri from data for attachment as image
                Uri selectedImageUri = data.getData();
                //set image on imagebutton to show the selection
                evidencia.setImageURI(selectedImageUri);
            }
        }
    }


    private void sendEmail(String evento, String comentario) {

        String mensaje = "Nombre del Evento:\n" + evento + "\n\n" + "Comentarios:\n" + comentario;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fingcarnetcultural@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registro Evento");

//        if (URI != null) {
//            emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
//        }
        if(URI != null){
            emailIntent.setType("image/jpeg");
            //attach image to email by telling intent the uri of image
            emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
        }

        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);

        try {

            startActivity(Intent.createChooser(emailIntent, "Choose an Email Client"));

        } catch (Exception e) {
            Toast.makeText(this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void openFolder() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.putExtra("return-data", true);
//        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);

        Intent intent = new Intent();
        // set type as image
        intent.setType("image/*");
    /*set action as ACTION_GET_CONTENT(Activity Action:
                Allow the user to select a particular kind of data and return it.)*/
        intent.setAction(Intent.ACTION_GET_CONTENT);
    /*start activity for result and pass create Chooser
                 and set title of dialog as Select Picture*/
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 1);
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        EmomentaneosFragment emomentaneosFragment = new EmomentaneosFragment();
        EpermanentesFragment epermanentesFragment = new EpermanentesFragment();

        List<EventosCarnet> eventosCarnet1 = emomentaneosFragment.obtenerEventos();
        List<EventosCarnet> eventosCarnet2 = epermanentesFragment.obtenerEventos();

        List<EventosCarnet> eventos_artisticos = new ArrayList<>();
        List<EventosCarnet> eventos_cientificos = new ArrayList<>();
        List<EventosCarnet> eventos_deportivos = new ArrayList<>();


        for (EventosCarnet evento : eventosCarnet1)
        {
            if (evento instanceof EventoArtistico)
                eventos_artisticos.add(evento);

            else if (evento instanceof EventoCientifico)
                eventos_cientificos.add(evento);

            else if (evento instanceof EventoDeportivo)
                eventos_deportivos.add(evento);
        }

        for (EventosCarnet evento : eventosCarnet2)
        {
            if (evento instanceof EventoArtistico)
                eventos_artisticos.add(evento);

            else if (evento instanceof EventoCientifico)
                eventos_cientificos.add(evento);

            else if (evento instanceof EventoDeportivo)
                eventos_deportivos.add(evento);
        }

        String seleccion = parent.getItemAtPosition(position).toString();

        ArrayList<String> opciones_spinner_evento = new ArrayList<>();

        if (seleccion.equals("Artístico")) {

            for (EventosCarnet evento_artistico : eventos_artisticos)
                opciones_spinner_evento.add(evento_artistico.getNombreEvento());

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item_estilo, opciones_spinner_evento);
            spinner_evento.setAdapter(adapter2);

        } else if (seleccion.equals("Científico")) {

            for (EventosCarnet evento_cientifico : eventos_cientificos)
                opciones_spinner_evento.add(evento_cientifico.getNombreEvento());

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item_estilo, opciones_spinner_evento);
            spinner_evento.setAdapter(adapter2);

        } else if (seleccion.equals("Deportivo")) {

            for (EventosCarnet evento_deportivo : eventos_deportivos)
                opciones_spinner_evento.add(evento_deportivo.getNombreEvento());

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item_estilo, opciones_spinner_evento);
            spinner_evento.setAdapter(adapter2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
