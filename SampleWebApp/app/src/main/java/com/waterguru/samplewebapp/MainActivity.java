package com.waterguru.samplewebapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    private boolean isWebViewLoaded = false;


    /*//////////////////////////////////////////////////////////
    // UI MEMBERS
    *///////////////////////////////////////////////////////////
    private Button btnLoad;
    private WebView mWebView;
    private EditText txtFirstName;
    private EditText txtLastName;
    private Button btnSayHello;
    private EditText txtWebAddress;


    /*//////////////////////////////////////////////////////////
    // UI PROPERTIES
    *///////////////////////////////////////////////////////////
    public String getFirstName() {
        String value = "";

        if((txtFirstName == null ? txtFirstName = (EditText) findViewById(R.id.txtFirstName) : txtFirstName) != null){
            value = txtFirstName.getText().toString();
        }

        return value;
    }
    public String getLastName() {
        String value = "";

        if((txtLastName == null ? txtLastName = (EditText) findViewById(R.id.txtLastName) : txtLastName) != null){
            value = txtLastName.getText().toString();
        }

        return value;
    }
    public void setWebAddress(String value) {
        if((txtWebAddress == null ? txtWebAddress = (EditText) findViewById(R.id.txtWebAddress) : txtWebAddress) != null){
            txtWebAddress.setText(value);
        }
    }
    public String getWebAddress() {
        String value = "";

        if((txtWebAddress == null ? txtWebAddress = (EditText) findViewById(R.id.txtWebAddress) : txtWebAddress) != null){
            value = txtWebAddress.getText().toString();
        }

        return value;
    }


    /*//////////////////////////////////////////////////////////
    // LIFE CYCLE OVERRIDES
    *///////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        super.onResume();

        syncUI();
    }


    /*//////////////////////////////////////////////////////////
    // BUTTON LISTENERS
    *///////////////////////////////////////////////////////////
    private void btnLoad_onClick() {
        if (btnLoad == null) {
            return;
        }

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getWebAddress().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter a Web Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                loadWebPage();
            }
        });
    }
    private void btnLoad_onLongClick() {
        if (btnLoad == null) {
            return;
        }

        btnLoad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setWebAddress("http://192.168.0.34:8080/examples/servlets/name_submission.html");
                return false;
            }
        });
    }
    private void btnSayHello_onClick() {
        if (btnSayHello == null) {
            return;
        }

        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isWebViewLoaded) {
                    return;
                }

                showWebAlert("Hello " + getFirstName() + " " + getLastName() + "!");
            }
        });
    }


    /*//////////////////////////////////////////////////////////
    // METHODS
    *///////////////////////////////////////////////////////////
    private void syncUI(){
        btnLoad = (Button) findViewById(R.id.btnLoad);
        mWebView = (WebView) findViewById(R.id.webView);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        btnSayHello = (Button) findViewById(R.id.btnSayHello);

        btnLoad_onClick();
        btnLoad_onLongClick();
        btnSayHello_onClick();
    }
    private void loadWebPage(){
        mWebView.setVisibility(View.VISIBLE);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                isWebViewLoaded = false;
                Toast.makeText(MainActivity.this, "Unable to Load Web Address", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                isWebViewLoaded = false;
                Toast.makeText(MainActivity.this, "Unable to Load Web Address", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                isWebViewLoaded = true;
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        mWebView.loadUrl(getWebAddress());
    }
    public void showWebAlert(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(JavaScriptHelper.getShowWebAlertMethod(message));
            }
        });
    }


    /*//////////////////////////////////////////////////////////
    // SCOPED CLASSES
    *///////////////////////////////////////////////////////////
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showNativeToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
