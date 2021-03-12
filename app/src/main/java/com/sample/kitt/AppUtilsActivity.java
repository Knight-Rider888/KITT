package com.sample.kitt;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import knight.rider.kitt.AppUtils;
import knight.rider.kitt.PermissionPackageUsageStatsUtils;
import knight.rider.kitt.bean.AppCacheInfo;
import knight.rider.kitt.listener.AppCacheListener;

public class AppUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_utils);
    }

    public void startAppInfoActivity(View view) {
        AppUtils.startAppInfoActivity(this);
    }

    public void getAppCache(View view) {

        if (Build.VERSION.SDK_INT >= 26) {

            if (!PermissionPackageUsageStatsUtils.checkPackageUsageStatsPermission(this)) {
                PermissionPackageUsageStatsUtils.requestPackageUsageStatsPermission(this);
                return;
            }
        }

        AppUtils.getAppCache(this, "org.jbase.yxt.oa", new AppCacheListener() {
            @Override
            public void getCache(AppCacheInfo appCacheInfo) {
                Toast.makeText(AppUtilsActivity.this, appCacheInfo.getCacheStr(AppUtilsActivity.this), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void getCacheError(String err) {
                Toast.makeText(AppUtilsActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });

    }
}