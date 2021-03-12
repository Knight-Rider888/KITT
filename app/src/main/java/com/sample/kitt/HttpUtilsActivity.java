package com.sample.kitt;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import knight.rider.kitt.http.HttpAuthorizationHeader;
import knight.rider.kitt.http.HttpParamsBuilder;

public class HttpUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_utils);
    }

    public void HttpParam(View view) {
        String s = HttpParamsBuilder
                .newBuilder()
                .addParams("userName", "haha")
                .addParams("userAge", "18")
                .buildJson();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void getAuthorization(View view) {
        Toast.makeText(this, HttpAuthorizationHeader.getBasicAuthorization("haha", "123456"), Toast.LENGTH_SHORT).show();
    }
}