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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Localizaciones extends AppCompatActivity {

    private LinearLayout botonesLayout;
    private static final String TAG = ""+R.string.localizacionesTAG ;
    private DatabaseReference mBBDD;
    private TextView ejemplo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizaciones);

        botonesLayout = (LinearLayout) findViewById(R.id.mContenedor);

        mBBDD = FirebaseDatabase.getInstance().getReference().
                child("localidades").child("loc01").child("localizaciones");

        ejemplo = (TextView) findViewById(R.id.mLocTitulo);

        mBBDD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Drawable back;
                //Obtenemos cada uno de los hijos y cargamos sus datos en botón
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    final Localizacion_BaseDatos post = postSnapshot.getValue(Localizacion_BaseDatos.class);
                    Button boton = new Button(getApplicationContext());
                    boton.setText(post.getNombre());
                    boton.setTextColor(Color.WHITE);
                    boton.setTypeface(null,Typeface.BOLD);
                    //Diferenciamos el estilo del botón por tipo de entorno
                    if(post.tipo.equals("Natural")){
                        back = getResources().getDrawable(R.drawable.localizaciones_boton_natural);
                    }else{
                        back = getResources().getDrawable(R.drawable.localizaciones_boton_normal);
                    }
                    boton.setBackground(back);
                    boton.setWidth(90);
                    final String ejemplo = (String) boton.getText();
                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent guia = new Intent(v.getContext(),Guia.class);
                            guia.putExtra("img",post.getImg1());
                            guia.putExtra("img2",post.getImg2());
                            guia.putExtra("img1desc",post.getImg1desc());
                            guia.putExtra("img2desc",post.getImg2desc());
                            guia.putExtra("titulo",post.getNombre());
                            guia.putExtra("lat",post.getLatitud());
                            guia.putExtra("lang",post.getAltitud());
                            guia.putExtra("audio",post.getAudio());
                            guia.putExtra("desc",post.getDescripcion());
                            startActivity(guia);
                        }
                    });
                    //Va agregegando botones al contenedor.
                    botonesLayout.addView(boton);
                    //Añadimos el margen del botón
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) boton.getLayoutParams();
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
