package com.sample.kitt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import knight.rider.kitt.WordUtils;

public class WordUtilsActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_utils);

        editText = (EditText) findViewById(R.id.et);
        textView = (TextView) findViewById(R.id.tv);
    }

    public void click(View view) {
        String s = editText.getText().toString();
        WordUtils.WordInfo wordInfo = WordUtils.getWordInfo(s);
        StringBuffer buffer = new StringBuffer(s);
        buffer.append("\n");
        buffer.append("拆分：" + Arrays.toString(wordInfo.getAllChar()));
        buffer.append("\n");
        buffer.append("非数字英文：" + Arrays.toString(wordInfo.getOtherChar()));
        buffer.append("\n");
        buffer.append("大写" + wordInfo.getUppercase());
        buffer.append("\n");
        buffer.append("小写" + wordInfo.getLowercase());
        buffer.append("\n");
        buffer.append("数字" + wordInfo.getNumber());
        textView.setText(buffer.toString());
    }
}