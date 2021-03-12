package knight.rider.kitt;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresPermission;

public class PermissionPackageUsageStatsUtils {

    /**
     * 检查 android.permission.PACKAGE_USAGE_STATS 权限是否获取
     *
     * @return true means allow
     */
    public static boolean checkPackageUsageStatsPermission(Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), context.getPackageName());

        return mode == AppOpsManager.MODE_ALLOWED;
    }


    /**
     * 请求 android.permission.PACKAGE_USAGE_STATS 权限获取
     */
    @RequiresPermission(Manifest.permission.PACKAGE_USAGE_STATS)
    public static void requestPackageUsageStatsPermission(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
