package com.waterguru.samplewebapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Sam Rosewall on 12/16/16.
 *
 * Interface between Android and the JavaScript file loaded in the URL of the WebView. This interface enables
 * JavaScript files to be able to call methods that are constructed natively.
 */
public class WebJavaScriptInterface {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    private Context mContext;


    /*//////////////////////////////////////////////////////////
    // CONSTRUCTOR
    *///////////////////////////////////////////////////////////
    /**
     * Instantiate the interface and set the context.
     * @param context
     */
    public WebJavaScriptInterface(Context context) {
        mContext = context;
    }


    /*//////////////////////////////////////////////////////////
    // EXTERNAL METHODS
    *///////////////////////////////////////////////////////////
    /**
     * Show a toast with a message passed from the WebView's JavaScript to the JavascriptInterface.
     * @param message string that is displayed in the toast
     */
    @JavascriptInterface
    public void showNativeToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

}
