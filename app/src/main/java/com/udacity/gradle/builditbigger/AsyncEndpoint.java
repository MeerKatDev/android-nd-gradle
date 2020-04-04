package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import androidx.core.util.Pair;

import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.backend.myApi.model.JokeBean;

import org.meerkatdev.andylib.JokeActivity;

import java.io.IOException;

class AsyncEndpoint extends AsyncTask<Pair, Void, String>  {

    private static MyApi mJokeApi = null;
    private Context mContext;
    private String mResult;
    private InterstitialAd mInterstitialAd;

    public AsyncEndpoint(Context context) {
        this.mContext = context;
    }

    @Override
    protected final String doInBackground(Pair... params) {
            if (mJokeApi == null) {
                MyApi.Builder builder = new MyApi.Builder(
                        AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(),
                        null)
                        .setRootUrl("https://");
                mJokeApi = builder.build();
            }

            try {
                return mJokeApi.postJoke(new JokeBean()).execute().getJoke();
            } catch (IOException e) {
                return e.getMessage();
            }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;
        // Setting the interstitial ad
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(mContext.getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                startJokeDisplayActivity();
            }

            @Override
            public void onAdClosed() {
                startJokeDisplayActivity();
                AdRequest ar = new AdRequest
                        .Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();
                mInterstitialAd.loadAd(ar);
            }

            private void startJokeDisplayActivity() {
                Intent intent = new Intent(mContext, JokeActivity.class);
                intent.putExtra(JokeActivity.ACTION_JOKE, mResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }

        });
    }

}