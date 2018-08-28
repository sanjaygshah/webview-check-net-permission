package com.sgs.jsg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private WebView webView;
    ProgressDialog Pdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView=(WebView) findViewById(R.id.webView1);
        WebSettings websettings = webView.getSettings();
        webView.getSettings().setSupportZoom(true);
        //webView.getSettings().setBuiltInZoomControls(true);
        // webView.getSettings().setDisplayZoomControls(true);
        websettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
// Todo if network available then this url otherwise other url --------------------
        if (isConnected()) {
            Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
            webView.loadUrl("file:///android_asset/www/homenew.html");
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            webView.loadUrl("file:///android_asset/www/home.html");
        }
        //todo over ?-------------

        //webView.loadUrl("https://sgsforjsg.github.io/home.html");
        // webView.loadUrl("https://sanjaygshah.github.io/mysite/");
    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void finishz(View view) {

        // Intent intent =new Intent(this,Permission.class);
        //Intent intent = new Intent(this,Main3Activity.class);
        // startActivity(intent);
        finish();
    }


    public class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);

            Pdialog = new ProgressDialog(Main2Activity.this);
            Pdialog.setTitle("JSG");
             Pdialog.setMessage("બંધુત્વ સે પ્રેમ...");
            Pdialog.show();

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // TODO Auto-generated method stub
            if (url.startsWith("tel:")) {
                //https://www.tutorialspoint.com/android/android_phone_calls.htm
                // change caod as per about link for direct dialing
                // Toast.makeText(getApplicationContext(),"Hello notification",Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            else if (url.startsWith("sms:")){
                //  Toast.makeText(getApplicationContext(),"colling",Toast.LENGTH_SHORT).show();
                finish();
               // view.loadUrl("file:///android_asset/www/index.html");
                return true;
            }
            //else if (url.startsWith("https://sgsforjsg.github.io/index.html")){
            //  Intent intent1 = new Intent(this,Main3Activity.class);
            // Intent i = new Intent("clicki_on_notificaion");
            //  startActivity(i);
            // }
            //else if (url.startsWith("Msg:")) { // whatsapp  ?
            //  view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            //  return true;
            // }
            //   Toast.makeText(getApplicationContext(),"Opening ",Toast.LENGTH_SHORT).show();

            view.loadUrl(url);
            return true;
        }


        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            Toast.makeText(getApplicationContext(),"Network Not available",Toast.LENGTH_SHORT).show();

            view.loadUrl("file:///android_asset/www/home.html");

            return;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            Pdialog.dismiss();

        }


    }
}
