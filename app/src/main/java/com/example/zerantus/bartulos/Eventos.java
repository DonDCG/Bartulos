package com.example.zerantus.bartulos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class Eventos extends AppCompatActivity {
    CalendarView calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        calendario = (CalendarView) findViewById(R.id.mCalendario);
    }
}
