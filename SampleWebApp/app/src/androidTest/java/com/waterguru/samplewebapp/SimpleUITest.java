package com.waterguru.samplewebapp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Sam Rosewall on 12/15/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleUITest {

    private String mValidString;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        mValidString = "Say Hello";
    }

    @Test
    public void btnSayHello_textIsValid() {
        onView(withId(R.id.btnSayHello)).check(matches(withText(mValidString)));
    }

}
