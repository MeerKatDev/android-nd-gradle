package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.meerkatdev.andylib.JokeActivity;

import java.lang.ref.WeakReference;

/**
 * Project contains a Java library for supplying jokes.
 *
 * Project contains an Android library with an activity that displays jokes passed to it as intent extras.
 *
 * Project contains a Google Cloud Endpoints module that supplies jokes from the Java library.
 * Project loads jokes from GCE module via an AsyncTask.
 *
 * Project contains connected tests to verify that the AsyncTask is indeed loading jokes.
 *
 * Project contains paid/free flavors. The paid flavor has no ads and no unnecessary dependencies.
 *
 * Ads are required in the free version.
 *
 * App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library. Note that the GCE module need only be deployed locally.
 *
 * BONUS
 *
 * Have the app display a loading indicator while the joke is being fetched from the server.
 * Write a Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new AsyncEndpointCompanion(this).execute();
    }

    private static class AsyncEndpointCompanion extends AsyncEndpoint {
        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        AsyncEndpointCompanion(MainActivity context) {
            super(context);
            activityReference = new WeakReference<>(context);
        }
        @Override
        protected void onPostExecute(String output) {
            Intent myIntent = new Intent(activityReference.get(), JokeActivity.class);
            myIntent.putExtra(JokeActivity.ACTION_JOKE, output);
            activityReference.get().startActivity(myIntent);
        }

    }

}
