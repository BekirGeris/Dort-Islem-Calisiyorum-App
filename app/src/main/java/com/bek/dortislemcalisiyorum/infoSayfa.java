package com.bek.dortislemcalisiyorum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class infoSayfa extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    Boolean gecisReklamAktıfleme = true;

    Button geri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sayfa);

        tanimlamalar();

        butonKontrolleri();

        uygulamaIDtanımlama();

        gecisReklam();
        bannerReklam();
    }

    public void tanimlamalar() {
        geri = findViewById(R.id.btn_set_geri);
    }

    public void butonKontrolleri() {
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    public void anaSayfayaGit() {
        Intent anasayfa = new Intent(infoSayfa.this, AnaSayfa.class);
        startActivity(anasayfa);
        finish();
    }

    public void  uygulamaIDtanımlama() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3194548974238198~3401169677");
    }


    public void gecisReklam() {
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
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

    public void bannerReklam() {
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
