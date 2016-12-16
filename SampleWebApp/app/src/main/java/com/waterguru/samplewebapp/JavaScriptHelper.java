package com.waterguru.samplewebapp;

/**
 * Created by Sam Rosewall on 12/14/16.
 *
 * Provides helper functions to construct JavaScript methods used in WebViews.
 */
public class JavaScriptHelper {

    /*//////////////////////////////////////////////////////////
    // EXTERNAL METHODS
    *///////////////////////////////////////////////////////////
    /**
     *
     * @param message string that is passed to the JavaScript method 'showWebAlert'
     * @return The 'showWebAlert' method with the passed message parameter that will be called in the associated JavaScript file.
     */
    public static String getShowWebAlertMethod(String message){
        return "javascript:showWebAlert('" + message + "')";
    }

}
