package com.example.zerantus.bartulos;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Alojamientos extends AppCompatActivity {

    private LinearLayout botonesLayout;
    private static final String TAG = "Alojamientos" ;
    private DatabaseReference mBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamientos);
        botonesLayout = (LinearLayout) findViewById(R.id.mAlojamientos);

        mBBDD = FirebaseDatabase.getInstance().getReference().
                child("localidades").child("loc01").child("alojamientos");

        mBBDD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Drawable back;
                //Obtenemos cada uno de los hijos y cargamos sus datos en botón
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    final Alojamiento_BaseDatos post = postSnapshot.getValue(Alojamiento_BaseDatos.class);
                    Button boton = new Button(getApplicationContext());
                    boton.setText(post.getNombre());
                    boton.setTextColor(Color.WHITE);
                    boton.setTypeface(null,Typeface.BOLD);
                    back = getResources().getDrawable(R.drawable.inicio_boton_inicio);
                    boton.setBackground(back);
                    boton.setWidth(90);
                    final String ejemplo = (String) boton.getText();
                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent guia = new Intent(v.getContext(),Alojamiento.class);
                            guia.putExtra("img",post.getImg1());
                            guia.putExtra("telef",post.getTelef());
                            guia.putExtra("lat",post.getLatitud());
                            guia.putExtra("lang",post.getAltitud());
                            guia.putExtra("nombre",post.getNombre());
                            guia.putExtra("desc",post.getDescripcion());
                            startActivity(guia);
                        }
                    });
                    //Va agregegando botones al contenedor.
                    botonesLayout.addView(boton);
                    //Añadimos el margen del botón
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) boton.getLayoutParams();
                    //boton.setLayoutParams(new LinearLayout.LayoutParams(10, 100));//TODO: Aumentar heigth de los botones
                    lp.setMargins(25,10,25,15);
                    boton.setLayoutParams(lp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error!", databaseError.toException());
            }
        });
    }
}
