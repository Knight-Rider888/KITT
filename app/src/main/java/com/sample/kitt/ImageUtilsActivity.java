package com.sample.kitt;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import knight.rider.kitt.ImageUtils;
import knight.rider.kitt.StorageUtils;
import knight.rider.kitt.mime.MediaFile;

public class ImageUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_utils);
    }

    @SuppressLint("MissingPermission")
    public void compressBitmap(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zip);
        Toast.makeText(this, "本demo,未动态申请权限，请手动开启权限", Toast.LENGTH_SHORT).show();
        @SuppressLint("MissingPermission") String shareFilePath = StorageUtils.getShareFilePath(null, "质量压缩.png", MediaFile.MediaFileType.getMediaType("压缩.png"));
        ImageUtils.compressToTargetKB(bitmap, shareFilePath, 30);
        MediaFile.scanMediaCenter(this, shareFilePath);
        bitmap.recycle();
        Toast.makeText(this, "保存位置:" + shareFilePath, Toast.LENGTH_SHORT).show();
    }
}