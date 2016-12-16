package com.waterguru.samplewebapp;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Sam Rosewall on 12/14/16.
 */
public class JavaScriptHelperTest {

    @Test
    public void getShowWebAlertMethod_returnsShowWebAlertInJavaScript(){
        String message = "Hello World";

        Assert.assertEquals("javascript:showWebAlert('" + message + "')", JavaScriptHelper.getShowWebAlertMethod(message));
    }

}
