package jp.gugu.kiken;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

public class MyWebViewActivity extends Activity
{

    private static WebView webview;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //webview = (WebView) findViewById(R.id.webview);

        webview = new WebView(this);
        setContentView(webview);
        //JavaScriptを有効に
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyWebViewClient() {});

        webview.loadUrl("http://www.luna-stella.sakura.ne.jp/guguru/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK  && webview.canGoBack() == true) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
            super();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if(!url.startsWith("http://www.luna-stella.sakura.ne.jp/guguru/")) {
                webview.stopLoading();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        }

    }
}

