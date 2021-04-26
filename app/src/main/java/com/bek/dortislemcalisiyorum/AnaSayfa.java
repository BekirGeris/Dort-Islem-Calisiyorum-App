package com.bek.dortislemcalisiyorum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AnaSayfa extends AppCompatActivity {

    private AdView mAdView;

    Boolean sesFlag = true;
    ImageButton info;
    ImageButton sesAyar;

    LinearLayout cokKolayOyun;
    LinearLayout kolayOyun;
    LinearLayout ortaOyun;
    LinearLayout zorOyun;

    MediaPlayer muzik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        //SharedPreferences GET = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor SET = GET.edit();

        tanimlamalar();
        //sesFlag = GET.getBoolean("sesF", true);


        //muzik = MediaPlayer.create(getApplicationContext(), R.raw.music);

        //muxikKontrol();
        //muzik.setLooping(true);

        buttonKontrol();

        uygulamaIDtanımlama();
        bannerReklam();

        /*sesAyar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesFlag = !sesFlag;
                muxikKontrol();
                SET.putBoolean("sesF", sesFlag);
                SET.commit();
            }
        });*/

        //onBackPressed();
    }

    public void tanimlamalar() {
        cokKolayOyun = findViewById(R.id.btn_cokKolay);
        kolayOyun = findViewById(R.id.btn_kolay);
        ortaOyun = findViewById(R.id.btn_orta);
        zorOyun = findViewById(R.id.btn_zor);
        info = findViewById(R.id.btn_info);
        //sesAyar = findViewById(R.id.btn_ses);
    }

    public void muxikKontrol() {
        if (sesFlag) {
            sesAyar.setImageResource(R.drawable.volume_up);
            if (muzik.isPlaying() == false) {
                muzik.start();
            }
            //ses acık
        } else {
            sesAyar.setImageResource(R.drawable.volume_off);
            if (muzik.isPlaying() == true) {
                muzik.pause();
            }
            //ses kapalı
        }
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

    public void uygulamaIDtanımlama() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3194548974238198~3401169677");
    }

    public void bannerReklam() {
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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