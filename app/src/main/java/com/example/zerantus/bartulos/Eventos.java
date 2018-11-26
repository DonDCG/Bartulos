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
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Eventos extends AppCompatActivity {
    private static final String TAG = ""+R.string.eventosTAG ;
    LinearLayout eventos;
    private DatabaseReference mBBDD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        eventos = (LinearLayout) findViewById(R.id.mEventos);

        mBBDD = FirebaseDatabase.getInstance().getReference().
                child("localidades").child("loc01").child("eventos");

        mBBDD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Drawable back;
                //Obtenemos cada uno de los hijos y cargamos sus datos en el LinearLayout
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    final Eventos_BaseDatos post = postSnapshot.getValue(Eventos_BaseDatos.class);
                    TextView evento = new TextView(getApplicationContext());
                    evento.setText(post.getEvento());
                    evento.setTextColor(Color.BLACK);
                    evento.setTypeface(null,Typeface.BOLD);
                    evento.setHeight(200);
                    evento.setTextSize(25);
                    evento.setPadding(15,0,0,0);
                    eventos.addView(evento);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, ""+R.string.error, databaseError.toException());
            }
        });
    }
}
