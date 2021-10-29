package com.bek.dortislemcalisiyorum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;


public class infoSayfa extends AppCompatActivity {

    private String gameId = "4251843";
    private Boolean testMode = false;
    private  String bannerId = "dortIslemBanner";
    private String interstitial = "dortIslemInter";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    Boolean gecisReklamAktıfleme = true;

    Button geri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sayfa);

        UnityAds.initialize (infoSayfa.this, gameId, testMode);

        tanimlamalar();

        butonKontrolleri();

        uygulamaIDtanımlama();

        unityAdsBaner();
        unityAdsVideo();
    }

    public  void unityAdsBaner(){
        LinearLayout linearLayout = findViewById(R.id.banner_unity_info);
        BannerView bannerView = new BannerView(infoSayfa.this, bannerId, new UnityBannerSize(328, 50));
        bannerView.load();
        linearLayout.addView(bannerView);
    }

    public void unityAdsVideo(){
        UnityAds.load(interstitial, new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String s) {

            }

            @Override
            public void onUnityAdsFailedToLoad(String s, UnityAds.UnityAdsLoadError unityAdsLoadError, String s1) {

            }
        });

        if (UnityAds.isReady(interstitial)){

            UnityAds.show(infoSayfa.this, interstitial, new IUnityAdsShowListener() {
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
        MobileAds.initialize(this, "ca-app-pub-1010640084929953~6066308490");
    }

    public void gecisReklam() {
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
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

        });
    }

}
