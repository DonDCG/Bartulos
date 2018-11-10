package com.example.zerantus.bartulos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Inicio extends AppCompatActivity {

    private ArrayList<Button> botonesIni;
    private Button mBtnLoc,mBtnRes,mBtnAloj,mBtnEvent,mBtnAjus,mBtnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mBtnLoc = findViewById(R.id.mBtnIni1);
        mBtnRes = findViewById(R.id.mBtnIni2);
        mBtnAloj = findViewById(R.id.mBtnIni3);
        mBtnEvent = findViewById(R.id.mBtnIni4);
        mBtnAjus = findViewById(R.id.mBtnIni5);
        mBtnSalir = findViewById(R.id.mBtnIni6);

        botonesIni = new ArrayList<>();
        botonesIni.add(mBtnLoc);botonesIni.add(mBtnRes);botonesIni.add(mBtnAloj);
        botonesIni.add(mBtnEvent);botonesIni.add(mBtnAjus);botonesIni.add(mBtnSalir);

        for (int i = 0;i<botonesIni.size();i++)
        {
            Button boton = botonesIni.get(i);
            final int index = i;
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(index){
                        case 0:startActivity(new Intent(Inicio.this,Localizaciones.class));
                            break;
                        case 1:startActivity(new Intent(Inicio.this,Restaurantes.class));
                            break;
                        case 2:startActivity(new Intent(Inicio.this,Alojamientos.class));
                            break;
                        case 3:startActivity(new Intent(Inicio.this,Eventos.class));
                            break;
                        case 4:startActivity(new Intent(Inicio.this,Ajustes.class));
                            break;
                        case 5:
                            break;
                        default: Toast.makeText(Inicio.this, "Error", Toast.LENGTH_SHORT).show();//TODO: Crear literal en strings
                            break;
                    }
                }
            });
        }
    }
}
