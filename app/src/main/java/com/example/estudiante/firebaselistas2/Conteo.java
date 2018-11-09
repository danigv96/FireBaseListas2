package com.example.estudiante.firebaselistas2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Conteo extends AppCompatActivity {


    Button btn_like1;
    Button btn_like2;

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteo);

        btn_like1= findViewById(R.id.btn_like1);
        btn_like2= findViewById(R.id.btn_like2);


        db= FirebaseDatabase.getInstance();

        btn_like1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.getReference().child("firmas").child("peticion1").push().setValue("L");


            }
        });


        btn_like2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.getReference().child("firmas").child("peticion2").push().setValue("N");

            }
        });

        db.getReference().child("firmas").child("peticion1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String likes= "Firmar("+dataSnapshot.getChildrenCount()+")";
                btn_like1.setText(likes);

                for (DataSnapshot dato : dataSnapshot.getChildren()){
                    Log.e("CLAVE", ""+dato.getKey());
                    Log.e("VALOR",""+dato.getValue());

                    String valor = dato.getValue(String.class);
                    Log.e("Valor transformado", ""+valor);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.getReference().child("firmas").child("peticion2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String likes= "Firmar("+dataSnapshot.getChildrenCount()+")";
                btn_like2.setText(likes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
