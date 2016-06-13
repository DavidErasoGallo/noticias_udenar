package com.example.root.noticias_Udenar;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class DetailActivity extends AppCompatActivity {

    //clase que carga cada noticia en un segundo activity
    // con un WebView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Recuperar url
        String urlExtra = getIntent().getStringExtra("url-extra");

        // Obtener WebView
        WebView webview = (WebView)findViewById(R.id.webview);


        // Habilitar Javascript en el renderizado
        webview.getSettings().setJavaScriptEnabled(true);

        // Transmitir localmente
        webview.setWebViewClient(new WebViewClient());

        // Cargar el contenido de la url
        webview.loadUrl(urlExtra);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
