package org.meerkatdev.andylib;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public final static String ACTION_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        String joke = getIntent().getStringExtra(ACTION_JOKE);
        TextView textViewJoke = findViewById(R.id.tv_joke);
        textViewJoke.setText(joke);
    }

}
