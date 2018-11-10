package com.example.zerantus.bartulos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        mBtnLoc = (Button) findViewById(R.id.mBtnIni1);
        mBtnRes = (Button) findViewById(R.id.mBtnIni2);
        mBtnAloj = (Button) findViewById(R.id.mBtnIni3);
        mBtnEvent = (Button) findViewById(R.id.mBtnIni4);
        mBtnAjus = (Button) findViewById(R.id.mBtnIni5);
        mBtnSalir = (Button) findViewById(R.id.mBtnIni6);

        botonesIni = new ArrayList<Button>();
        botonesIni.add(mBtnLoc);botonesIni.add(mBtnRes);botonesIni.add(mBtnAloj);
        botonesIni.add(mBtnEvent);botonesIni.add(mBtnAjus);botonesIni.add(mBtnSalir);

        for (Button boton:botonesIni)
        {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(botonesIni.get(1)){

                    }
                }
            });
        }
    }
}
