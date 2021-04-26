package com.bek.dortislemcalisiyorum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SonucActivity<lateinit, mInterstitialAd, val> extends AppCompatActivity {

    String dogruCevap;
    String yanlisCevap;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    Button anaSayfa;

    String dogruS, yanlisS, cevapS, oranS;

    TextView cevapSayisi;
    TextView dogruCevapSayisi;
    TextView yanlisCevapSayisi;
    TextView oran;
    TextView dorgruCevaplar;
    TextView yanlisCevaplar;

    Boolean gecisReklamAktıfleme = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        uygulamaIDTanımlama();

        tanimlamalar();

        oncekiSayfadanBilgileriAl();

        ekranaBilgileriBas();

        gecisReklam();

        anaSayfayaGit();

        bannerReklam();
    }

    public void uygulamaIDTanımlama() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3194548974238198~3401169677");
    }

    public void tanimlamalar() {
        anaSayfa = findViewById(R.id.btn_ana_sayfa);
        cevapSayisi = findViewById(R.id.text_cevapSayisi);
        dogruCevapSayisi = findViewById(R.id.text_dogruSayisi);
        yanlisCevapSayisi = findViewById(R.id.text_yanlisSayisi);
        oran = findViewById(R.id.text_oran);
        dorgruCevaplar = findViewById(R.id.text_dogruCevaplar);
        yanlisCevaplar = findViewById(R.id.text_yanlisCevaplar);
    }

    public void oncekiSayfadanBilgileriAl() {
        Bundle sonucSayfa = getIntent().getExtras();
        dogruS = sonucSayfa.getString("Ds");
        yanlisS = sonucSayfa.getString("Ys");
        cevapS = sonucSayfa.getString("Cs");
        oranS = sonucSayfa.getString("Oran");
        dogruCevap = sonucSayfa.getString("dC");
        yanlisCevap = sonucSayfa.getString("yC");
    }

    public void ekranaBilgileriBas() {
        cevapSayisi.setText(" " + cevapS);
        dogruCevapSayisi.setText(" " + dogruS);
        yanlisCevapSayisi.setText(" " + yanlisS);
        oran.setText("%" + oranS);
        dorgruCevaplar.setText(dorgruCevaplar.getText() + dogruCevap);
        yanlisCevaplar.setText(yanlisCevaplar.getText() + yanlisCevap);
    }

    public void gecisReklam() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3194548974238198/8392915558");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(gecisReklamAktıfleme){
                    mInterstitialAd.show();
                    gecisReklamAktıfleme = !gecisReklamAktıfleme;
                }
            }
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
    }

    public void anaSayfayaGit() {
        anaSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent anasayfa = new Intent(SonucActivity.this, AnaSayfa.class);
                startActivity(anasayfa);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.str_uyari));
        builder.setPositiveButton(getString(R.string.str_evet), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Evet'e basılınca yapılacak işlemleri yazınız
                System.exit(0);
            }
        });
        builder.setNegativeButton(getString(R.string.str_hayir), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Hayır'a baslınca yapılacak işmeleri yazınız
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void bannerReklam() {
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}