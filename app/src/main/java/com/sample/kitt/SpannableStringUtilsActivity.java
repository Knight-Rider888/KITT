package com.sample.kitt;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import knight.rider.kitt.SpannableStringUtils;

public class SpannableStringUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_utils);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);

        SpannableStringBuilder spannableStringBuilder = SpannableStringUtils.equalsSpecialColor("divabcdivbbdivcdivddediv", "div", Color.parseColor("#920000"));
        tv1.setText(spannableStringBuilder);
        SpannableStringBuilder spannableStringBuilder2 = SpannableStringUtils.equalsSpecialAllColor("divabcdivbbdivcdivddediv", "div", Color.parseColor("#920000"));
        tv2.setText(spannableStringBuilder2);

    }
}