package com.sample.kitt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void AppUtils(View view) {
        startActivity(new Intent(this, AppUtilsActivity.class));
    }

    public void MD5Utils(View view) {
        startActivity(new Intent(this, MD5UtilsActivity.class));
    }

    public void StorageUtils(View view) {
        startActivity(new Intent(this, StorageUtilsActivity.class));
    }

    public void HttpUtils(View view) {
        startActivity(new Intent(this, HttpUtilsActivity.class));
    }

    public void ImageUtils(View view) {
        startActivity(new Intent(this, ImageUtilsActivity.class));
    }

    public void WordUtils(View view) {
        startActivity(new Intent(this, WordUtilsActivity.class));
    }

    public void SpannableStringUtils(View view) {
        startActivity(new Intent(this, SpannableStringUtilsActivity.class));
    }
}