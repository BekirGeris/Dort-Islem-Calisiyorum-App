package com.bek.dortislemcalisiyorum;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Sorular extends AppCompatActivity {

    String uyari;
    String dogruCevap = "";
    String yanlisCevap = "";

    private AdView mAdView;

    ProgressBar yuklemeCubugu;

    double sayac = 105;
    int dogruCevapSayisi = 0, yanlisCevapSayisi = 0, cevapSayisi = 0, oran = 0;
    int cevap = -1;
    int sonuc = 0;

    String butoonAdi;

    TextView sorularEkran;
    TextView dorgruCevaplar;
    TextView yanlisCevaplar;

    EditText edtCevap;

    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button gonder;
    Button sil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular);

        tanimalamalar();

        oncekiSayfaBilgileriniAl();

        seviyeTespiti();

        butonlar();

       silButonu();

        progresBarAyarKontrol();

        bannerReklam();

    }

    public void tanimalamalar() {
        yuklemeCubugu = findViewById(R.id.pro_bar);
        sorularEkran = findViewById(R.id.text_sorularEkran);
        gonder = findViewById(R.id.btn_Enter);
        edtCevap = findViewById(R.id.edt_cevap);
        sil = findViewById(R.id.btn_sil);
        b0 = findViewById(R.id.btn_0);
        b1 = findViewById(R.id.btn_1);
        b2 = findViewById(R.id.btn_2);
        b3 = findViewById(R.id.btn_3);
        b4 = findViewById(R.id.btn_4);
        b5 = findViewById(R.id.btn_5);
        b6 = findViewById(R.id.btn_6);
        b7 = findViewById(R.id.btn_7);
        b8 = findViewById(R.id.btn_8);
        b9 = findViewById(R.id.btn_9);
        dorgruCevaplar = findViewById(R.id.text_dogruCevaplar);
        yanlisCevaplar = findViewById(R.id.text_yanlisCevaplar);
        uyari = getString(R.string.app_name);
    }

    public void oncekiSayfaBilgileriniAl() {
        Bundle sorular = getIntent().getExtras();
        butoonAdi = sorular.getString("ButonAdı");
    }

    public void seviyeTespiti() {

        if (Objects.equals(butoonAdi, "CokKolay")) {
            sonuc = Seviye(5);
            gonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editKontrol() && String.valueOf(edtCevap.getText()).length() < 4) {
                        cevap = getCevap();
                        if (cevap == sonuc) {
                            dogruCevap = dogruCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            dogruCevapSayisi++;
                            cevapSayisi++;
                        }else {
                            yanlisCevap = yanlisCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            yanlisCevapSayisi++;
                            cevapSayisi++;
                        }
                        sonuc = Seviye(5);
                        edtCevap.setText("");
                    }else {

                        Toast.makeText(getApplicationContext(), getString(R.string.str_hata), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

        if (Objects.equals(butoonAdi, "Kolay")){

            sonuc = Seviye(9);
            gonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editKontrol() && String.valueOf(edtCevap.getText()).length() < 4) {
                        cevap = getCevap();
                        if (cevap == sonuc) {
                            dogruCevap = dogruCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            dogruCevapSayisi++;
                            cevapSayisi++;
                        }else {
                            yanlisCevap = yanlisCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            yanlisCevapSayisi++;
                            cevapSayisi++;
                        }
                        sonuc = Seviye(9);
                        edtCevap.setText("");
                    }else {

                        Toast.makeText(getApplicationContext(), getString(R.string.str_hata), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }else if (Objects.equals(butoonAdi, "Orta")) {

            sonuc = Seviye(14);
            gonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editKontrol() && String.valueOf(edtCevap.getText()).length() < 4) {
                        cevap = getCevap();
                        if (cevap == sonuc) {
                            dogruCevap = dogruCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            dogruCevapSayisi++;
                            cevapSayisi++;
                        }else {
                            yanlisCevap = yanlisCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            yanlisCevapSayisi++;
                            cevapSayisi++;
                        }
                        sonuc = Seviye(14);
                        edtCevap.setText("");
                    }else {

                        Toast.makeText(getApplicationContext(), getString(R.string.str_hata), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }else  if (Objects.equals(butoonAdi, "Zor")) {

            sonuc = Seviye(29);
            gonder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editKontrol() && String.valueOf(edtCevap.getText()).length() < 5) {
                        cevap = getCevap();
                        if (cevap == sonuc) {
                            dogruCevap = dogruCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            dogruCevapSayisi++;
                            cevapSayisi++;
                        }else {
                            yanlisCevap = yanlisCevap + "\n" + sorularEkran.getText() + "=>" + getCevap();
                            yanlisCevapSayisi++;
                            cevapSayisi++;
                        }
                        sonuc = Seviye(29);
                        edtCevap.setText("");
                    }else {

                        Toast.makeText(getApplicationContext(), getString(R.string.str_hata), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    public int getCevap() {
        return Integer.parseInt(edtCevap.getText().toString());
    }

    public Boolean editKontrol() {
        if(edtCevap.getText().toString().trim().equals("") ) {
            return false;
        }
        return true;
    }

    public int Seviye(int i) {
        char islem = rasgeleIslem();
        int sayi1 = 0;
        int sayi2 = 0;
        int hesaplanaSonuc = 0;

        switch (islem) {
            case 'x':
                sayi1 = (int)(Math.random()*i + (i / 4));
                sayi2 = (int)(Math.random()*i + (i / 4));
                hesaplanaSonuc = sayi1 * sayi2;
                break;
            case '-':
                do {
                    sayi1 = (int)(Math.random()*(i * 2) + (i / 3));
                    sayi2 = (int)(Math.random()*(i * 2) + (i / 3));
                }while (sayi1 <= sayi2);
                hesaplanaSonuc = sayi1 - sayi2;
                break;
            case '+':
                sayi1 = (int)(Math.random()*(i * 2) + (i / 3));
                sayi2 = (int)(Math.random()*(i * 2) + (i / 3));
                hesaplanaSonuc = sayi1 + sayi2;
                break;
            case '/':
                do {
                    sayi1 = (int)(Math.random()*(i * 3) + (i / 3));
                    sayi2 = (int)(Math.random()*(i * 3) + (i / 3));
                }while (sayi1 % sayi2 != 0 || sayi1 == sayi2);
                hesaplanaSonuc = sayi1 / sayi2;
                break;
        }
        ekranaBas(sayi1, sayi2, islem);
        return hesaplanaSonuc;
    }

    public char rasgeleIslem() {

        char[] islemler;

        islemler = new char[]{'-', '+', 'x', '/'};

        return islemler[(int) (Math.random() * 4)];
    }

    public void ekranaBas(int sayi1, int sayi2, char islem) {

        sorularEkran.setText(String.valueOf(sayi1) + " " + islem +" "+ String.valueOf(sayi2) + " = ?");

    }

    public void butonlar() {
        butonKulanimi(b0,b0.getText().toString());
        butonKulanimi(b1,b1.getText().toString());
        butonKulanimi(b2,b2.getText().toString());
        butonKulanimi(b3,b3.getText().toString());
        butonKulanimi(b4,b4.getText().toString());
        butonKulanimi(b5,b5.getText().toString());
        butonKulanimi(b6,b6.getText().toString());
        butonKulanimi(b7,b7.getText().toString());
        butonKulanimi(b8,b8.getText().toString());
        butonKulanimi(b9,b9.getText().toString());
    }

    public void butonKulanimi(Button buton, String butonNo) {
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(edtCevap.getText()).length() < 6) {
                    edtCevap.setText(edtCevap.getText() + butonNo);
                }
            }
        });
    }

    public void  silButonu() {
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int length = edtCevap.getText().length();
                if (length > 0) {
                    edtCevap.getText().delete(length - 1, length);
                }

            }
        });
    }

    public void progresBarAyarKontrol() {
        //zamanlayıcı
        Timer zamanlayici = new Timer();

        //zamanlayıcı gorevi
        TimerTask zamanlayiciGorevi = new TimerTask() {
            @Override
            public void run() {

                sayac -= 0.3;

                //Progresse sayacı atamak
                yuklemeCubugu.setProgress((int)sayac);

                //sayac kaca kadar
                if (sayac < 0) {
                    zamanlayici.cancel();
                    sonucSayfasinaGit();
                }

            }
        };

        //zamanlayıcı cizelgesini ayarlama
        zamanlayici.schedule(zamanlayiciGorevi, 0, 100);
    }

    private void sonucSayfasinaGit() {
        Intent sonucSayfa = new Intent(Sorular.this, SonucActivity.class);
        sonucSayfa.putExtra("Ds", String.valueOf(dogruCevapSayisi));
        sonucSayfa.putExtra("Ys", String.valueOf(yanlisCevapSayisi));
        sonucSayfa.putExtra("Cs", String.valueOf(cevapSayisi));
        oran = (int)((double)(dogruCevapSayisi) / (double) (cevapSayisi) * 100);
        sonucSayfa.putExtra("Oran", String.valueOf(oran));
        sonucSayfa.putExtra("dC", dogruCevap);
        sonucSayfa.putExtra("yC", yanlisCevap);
        startActivity(sonucSayfa);
        finish();
    }

    public void testKaydınıSonucSayfasindaGösterD(String[] dogruCevap) {
        for (int i = 0; i <= dogruCevapSayisi; i++) {
            dorgruCevaplar.setText(dorgruCevaplar.getText() + dogruCevap[i] + "\n");
        }
    }

    public void testKaydınıSonucSayfasindaGösterY(String[] yanlisCevap) {
        for (int i = 0; i <= yanlisCevapSayisi; i++) {
            yanlisCevaplar.setText(yanlisCevaplar.getText() + yanlisCevap[i] + "\n");
        }
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

    public void bannerReklam() {
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3194548974238198~3401169677");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}