package tiksem.com.books;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.pollfish.constants.Position;
import com.pollfish.main.PollFish;

import tiksem.com.penis.R;

public class MyActivity extends Activity {
    private WebView webView;
    private WebView banner;

    @Override
    public void onResume() {
        super.onResume();
        PollFish.init(this, "d068a840-3cd7-4ef6-a06d-25839a7b510a", Position.BOTTOM_LEFT, 5);
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
