package com.example.zerantus.bartulos;

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
    private static final String TAG = "Localiaciones" ;
    private DatabaseReference mBBDD;
    private TextView ejemplo;
    private ValueEventListener eventListener;
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
                    Localizacion_BaseDatos post = postSnapshot.getValue(Localizacion_BaseDatos.class);
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
                            Toast.makeText(getApplicationContext(), ejemplo, Toast.LENGTH_SHORT).show();
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
