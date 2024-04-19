package com.rotech.examepsicotecnico;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class Resultado extends Activity {

    Intent i;

    View.OnTouchListener repetirTeste = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            i = new Intent(Resultado.this, MainActivity.class);
            startActivity(i);
            finish();
            return false;
        }
    };

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        findViewById(R.id.tvpq).setOnTouchListener(repetirTeste);
        findViewById(R.id.tvResultado).setOnTouchListener(repetirTeste);

        final Classificador nota = new Classificador();
        Bundle bundle = getIntent().getExtras();
        nota.setPontuacao(bundle.getInt("nota"));

        TextView tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setText(getResources().getText(R.string.sResultado) + " " + String.valueOf(nota.getPontuacao())
                + " de 18");
    }
}
