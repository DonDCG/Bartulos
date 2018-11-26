package com.example.zerantus.bartulos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class Alojamiento extends AppCompatActivity implements OnMapReadyCallback {

    private ImageView img;
    private TextView titulo, descripcion;
    private Button llamada;
    private float lat,lang;
    private String ruta;
    private MapFragment mMap;
    private GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamiento);

        titulo = (TextView) findViewById(R.id.mAlojTitulo);
        descripcion = (TextView) findViewById(R.id.mTextAlojInfo);
        img = (ImageView) findViewById(R.id.mImgAloj);
        llamada = (Button) findViewById(R.id.mBtnLlamarAloj);

        final String telefono =  (String) getIntent().getExtras().get("telef");

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial = "tel:" + telefono;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        titulo.setText((String) getIntent().getExtras().get("nombre"));
        descripcion.setText((String) getIntent().getExtras().get("desc"));

        mMap = (MapFragment) getFragmentManager().findFragmentById(R.id.mMapAloj);
        mMap.getMapAsync(this);

        String aux = (String) getIntent().getExtras().get("lat");
        lat = Float.valueOf(aux);
        aux = (String) getIntent().getExtras().get("lang");
        lang = Float.valueOf(aux);

        ruta = (String) getIntent().getExtras().get("img");
        CargarImagen(ruta,img);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

        gmap.setIndoorEnabled(true);
        UiSettings uiSettings = gmap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        gmap.setMinZoomPreference(12);

        LatLng localizacion = new LatLng(lat, lang);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(localizacion);
        gmap.addMarker(markerOptions);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));
    }
    private void CargarImagen(String ruta, ImageView imagen){
        Picasso.get()
                .load(ruta)
                .fit()
                .centerCrop()
                .into(imagen);
    }
}
