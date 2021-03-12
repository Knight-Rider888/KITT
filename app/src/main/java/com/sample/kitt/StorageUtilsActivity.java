package com.sample.kitt;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import knight.rider.kitt.mime.MediaFile;

public class StorageUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_utils);
    }

    public void getMimeType(View view) {
        String path = ".../测试包.apk";
        String mimeType = MediaFile.MediaFileType.getMediaType(path).mimeType;
        Toast.makeText(this, "地址：" + path + "  的 mimeType是：" + mimeType, Toast.LENGTH_SHORT).show();
    }

    public void getFileTitle(View view) {
        String path = ".../测试包.apk";
        String title = MediaFile.getFileTitleByKnownMimeType(path);
        Toast.makeText(this, "地址：" + path + "  的 title是：" + title, Toast.LENGTH_SHORT).show();
    }
}