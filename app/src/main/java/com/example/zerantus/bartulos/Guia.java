package com.example.zerantus.bartulos;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class Guia extends AppCompatActivity  implements OnMapReadyCallback{

    private StorageReference mStorage;
    private ProgressDialog progressDialog;

    private ImageView img1,img2;
    private TextView img1desc,img2desc,titulo,descripcion;

    private MediaPlayer mReproductor;
    private Button mBtnPlay,mBtnStop,mBtnAtras,mBtnAlante;
    private boolean activo = false;

    private float lat,lang;
    private String ruta;
    private MapFragment mMap;
    private GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        progressDialog= new ProgressDialog(this);
        progressDialog.show();

        mReproductor = new MediaPlayer();
        mStorage = FirebaseStorage.getInstance().getReference();
        Uri uri =  Uri.parse((String) getIntent().getExtras().get("audio"));
        mReproductor = MediaPlayer.create(this,uri);


        mBtnPlay = (Button) findViewById(R.id.mBtnPlay);
        mBtnStop = (Button) findViewById(R.id.mBtnStop);
        mBtnAtras = (Button) findViewById(R.id.mBtnRev);
        mBtnAlante = (Button) findViewById(R.id.mBtnFf);

        img1 = (ImageView) findViewById(R.id.mImgGuia);
        img2 = (ImageView) findViewById(R.id.mImgGuia2);

        titulo = (TextView) findViewById(R.id.mGuiaTitulo);
        img1desc = (TextView) findViewById(R.id.mTextImgDes);
        img2desc = (TextView) findViewById(R.id.mTextImg2Des);
        descripcion = (TextView) findViewById(R.id.mTextDescripcionGuia);

        mMap = (MapFragment) getFragmentManager().findFragmentById(R.id.mMap);
        mMap.getMapAsync(this);

        //Añadimos en pie de foto a cada ImageView
        titulo.setText((String) getIntent().getExtras().get("titulo"));
        img1desc.setText((String) getIntent().getExtras().get("img1desc"));
        img2desc.setText((String) getIntent().getExtras().get("img2desc"));
        descripcion.setText((String) getIntent().getExtras().get("desc"));

        //Cargamos la imagenes en sus ImageView
        ruta = (String) getIntent().getExtras().get("img");
        CargarImagen(ruta,img1);
        ruta = (String) getIntent().getExtras().get("img2");
        CargarImagen(ruta,img2);

        String aux = (String) getIntent().getExtras().get("lat");
        lat = Float.valueOf(aux);
        aux = (String) getIntent().getExtras().get("lang");
        lang = Float.valueOf(aux);
        
        //Listener botones reproductor
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mReproductor.isPlaying()){
                    mBtnPlay.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_play, 0, 0, 0);
                    mReproductor.pause();
                }else{
                    mBtnPlay.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_pausa, 0, 0, 0);
                    mReproductor.start();
                }
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mReproductor.isPlaying()){
                    mBtnPlay.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_play, 0, 0, 0);
                    mReproductor.pause();
                    mReproductor.seekTo(0);
                }
            }
        });
        mBtnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mReproductor.getCurrentPosition()>10000){
                    mReproductor.seekTo(mReproductor.getCurrentPosition()-10000);
                }else{
                    mReproductor.seekTo(0);
                }
            }
        });
        mBtnAlante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReproductor.seekTo(mReproductor.getCurrentPosition()+10000);
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);
    }

    private void CargarImagen(String ruta, ImageView imagen){
        Picasso.get()
                .load(ruta)
                .fit()
                .centerCrop()
                .into(imagen);
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
    public void onDestroy(){
        super.onDestroy();
        mReproductor.stop();
        mReproductor.release();
        mReproductor = null;
    }
}
