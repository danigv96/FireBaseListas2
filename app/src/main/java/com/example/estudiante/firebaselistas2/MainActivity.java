package com.example.estudiante.firebaselistas2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    FirebaseDatabase db;
    FirebaseListAdapter<Usuario> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        lista= findViewById(R.id.lista);

        Query ref = db.getReference().child("Usuarios");

        FirebaseListOptions<Usuario> options = new FirebaseListOptions
                .Builder<Usuario>()
                .setLayout(R.layout.renglon)
                .setQuery(ref, Usuario.class)
                .build();



        adapter = new FirebaseListAdapter<Usuario>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Usuario model, int position) {
                TextView renglon_correo = v.findViewById(R.id.renglon_correo);
                TextView renglon_nombre = v.findViewById(R.id.renglon_nombre);
                TextView renglon_uid = v.findViewById(R.id.renglon_uid);

                renglon_correo.setText(model.correo);
                renglon_nombre.setText(model.nombre);
                renglon_uid.setText(model.uid);
            }

        };

        lista.setAdapter(adapter);


    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
