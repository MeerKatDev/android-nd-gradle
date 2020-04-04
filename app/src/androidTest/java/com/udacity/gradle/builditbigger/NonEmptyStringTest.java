package com.udacity.gradle.builditbigger;

import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static java.security.AccessController.getContext;
import static junit.framework.TestCase.assertNotNull;

public class NonEmptyStringTest {

    private static final String LOG_TAG = "NonEmptyStringTest";

    @SuppressWarnings("unchecked")
    @Test
    public void asyncEndpointTest() {


        // Testing that Async task successfully retrieves a non-empty string
        // You can test this from androidTest -> Run 'All Tests'
        Log.v("NonEmptyStringTest", "Running NonEmptyStringTest test");
        String result = null;
        AsyncEndpoint endpointsAsyncTask = new AsyncEndpoint(InstrumentationRegistry.getInstrumentation().getTargetContext());
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}