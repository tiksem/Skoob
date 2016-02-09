package tiksem.com.books;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import tiksem.com.penis.R;

public class MyActivity extends Activity {
    private WebView webView;
    private WebView banner;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        webView = (WebView)findViewById(R.id.content);

        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("market://") || url.startsWith("https://play.google.com")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                webView.clearView();
                webView.loadUrl(url);
                return true;
            }
        };
        webView.setWebViewClient(webViewClient);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/book/index.html");
    }
}
