package com.bek.dortislemcalisiyorum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.view.View;

import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class AnaSayfa extends AppCompatActivity{

    private String gameId = "4251843";
    private Boolean testMode = false;
    private  String bannerId = "dortIslemBanner";
    private String interstitial = "dortIslemInter";

    ImageButton info;
    LinearLayout cokKolayOyun;
    LinearLayout kolayOyun;
    LinearLayout ortaOyun;
    LinearLayout zorOyun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        UnityAds.initialize (AnaSayfa.this, gameId, testMode);

        tanimlamalar();

        buttonKontrol();

        unityAdsBaner();
    }

    public  void unityAdsBaner(){
        LinearLayout linearLayout = findViewById(R.id.banner_unity_ana);
        BannerView bannerView = new BannerView(AnaSayfa.this, bannerId, new UnityBannerSize(328, 50));
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

            UnityAds.show(AnaSayfa.this, interstitial, new IUnityAdsShowListener() {
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
        cokKolayOyun = findViewById(R.id.btn_cokKolay);
        kolayOyun = findViewById(R.id.btn_kolay);
        ortaOyun = findViewById(R.id.btn_orta);
        zorOyun = findViewById(R.id.btn_zor);
        info = findViewById(R.id.btn_info);
    }

    public void buttonKontrol() {

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoSayfasinaGit();
            }
        });

        cokKolayOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sorular sayfasına git
                Intent sorular = new Intent(getApplicationContext(), Sorular.class);
                sorular.putExtra("ButonAdı", "CokKolay");
                startActivity(sorular);
                finish();

            }
        });

        kolayOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sorular sayfasına git
                Intent sorular = new Intent(getApplicationContext(), Sorular.class);
                sorular.putExtra("ButonAdı", "Kolay");
                startActivity(sorular);
                finish();

            }
        });

        ortaOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sorular sayfasına git
                Intent sorular = new Intent(getApplicationContext(), Sorular.class);
                sorular.putExtra("ButonAdı", "Orta");
                startActivity(sorular);
                finish();

            }
        });

        zorOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sorular sayfasına git
                Intent sorular = new Intent(getApplicationContext(), Sorular.class);
                sorular.putExtra("ButonAdı", "Zor");
                startActivity(sorular);
                finish();

            }
        });
    }

    public void infoSayfasinaGit() {
        Intent infoSayfa = new Intent(AnaSayfa.this, infoSayfa.class);
        startActivity(infoSayfa);
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