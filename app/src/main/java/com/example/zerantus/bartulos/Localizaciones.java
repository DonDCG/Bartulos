package com.example.zerantus.bartulos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Localizaciones extends AppCompatActivity {

    private LinearLayout botonesLayout;
    private static final String TAG = "Localiaciones" ;
    private DatabaseReference mBBDD;
    private TextView ejemplo;
    private ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizaciones);

        botonesLayout = (LinearLayout) findViewById(R.id.mContenedor);
        /*botonesLayout = new LinearLayout(getApplicationContext());
        botonesLayout.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        botonesLayout.setOrientation(LinearLayout.VERTICAL);
        botonesLayout.setGravity(Gravity.CENTER);*/

        mBBDD = FirebaseDatabase.getInstance().getReference().
                child("localidades").child("loc01").child("localizaciones");

        ejemplo = (TextView) findViewById(R.id.ejemplo);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Localizacion_BaseDatos pred = dataSnapshot.getValue(Localizacion_BaseDatos.class);
                ejemplo.setText(pred.getNombre());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error!", databaseError.toException());
            }
        };
        mBBDD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Obtenemos cada uno de los hijos y cargamos sus datos en botón
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Localizacion_BaseDatos post = postSnapshot.getValue(Localizacion_BaseDatos.class);
                    ejemplo.setText(post.getNombre());
                    Button boton = new Button(getApplicationContext());
                    boton.setText(post.getNombre());
                    boton.setBackgroundColor(Color.GREEN);

                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), " Listener botón " + v.getTag(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    //Va agregegando botones al contenedor.
                    botonesLayout.addView(boton);
                    /*Localizacion_BaseDatos post = postSnapshot.getValue(Localizacion_BaseDatos.class);
                    ejemplo.setText(post.getNombre());*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error!", databaseError.toException());
            }
        });

    }
}
