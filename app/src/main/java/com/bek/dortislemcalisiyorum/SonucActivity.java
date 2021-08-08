package com.bek.dortislemcalisiyorum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class SonucActivity<lateinit, mInterstitialAd, val> extends AppCompatActivity {

    private String gameId = "*******";
    private Boolean testMode = true;
    private  String bannerId = "dortIslemBanner";
    private String interstitial = "Interstitial_Android";

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

        UnityAds.initialize (SonucActivity.this, gameId, testMode);

        uygulamaIDTanımlama();

        tanimlamalar();

        oncekiSayfadanBilgileriAl();

        ekranaBilgileriBas();

        anaSayfaBtnKontrol();

        if (Integer.parseInt(oranS) < 90){
            unityAdsVideo();
        }

        unityAdsBaner();
    }

    public  void unityAdsBaner(){
        LinearLayout linearLayout = findViewById(R.id.banner_unity);
        BannerView bannerView = new BannerView(SonucActivity.this, bannerId, new UnityBannerSize(328, 50));
        bannerView.load();
        linearLayout.addView(bannerView);
    }

    public  void unityAdsVideo(){
        UnityAds.load(interstitial, new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String s) {

            }

            @Override
            public void onUnityAdsFailedToLoad(String s, UnityAds.UnityAdsLoadError unityAdsLoadError, String s1) {

            }
        });

        if (UnityAds.isReady(interstitial)){

            UnityAds.show(SonucActivity.this, interstitial, new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {

                }

                @Override
                public void onUnityAdsShowStart(String s) {

                }

                @Override
                public void onUnityAdsShowClick(String s) {

                }

                @Override
                public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState unityAdsShowCompletionState) {

                }
            });
        }
    }


    public void uygulamaIDTanımlama() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-1010640084929953~6066308490");
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
       /* mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1010640084929953/1405008751");
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

        });*/
    }

    public void anaSayfaBtnKontrol() {
        anaSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anaSayfayaGit();
            }
        });
    }

    public void anaSayfayaGit(){
        Intent anasayfa = new Intent(SonucActivity.this, AnaSayfa.class);
        startActivity(anasayfa);
        finish();
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
}