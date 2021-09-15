package knight.rider.kitt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;

public class NotificationUtils {

    /**
     * 通知是否可用
     */
    public static boolean notificationEnabled(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    /**
     * 跳转通知设置页面
     */
    @SuppressLint("InlinedApi")
    public static void startNotificationSettingActivity(Context context) {

        int uid = context.getApplicationInfo().uid;
        String packageName = context.getApplicationContext().getPackageName();

        // 进入设置应用通知权限界面
        try {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, uid);
            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
            intent.putExtra("app_package", packageName);
            intent.putExtra("app_uid", uid);
            context.startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", packageName, null));
            context.startActivity(intent);
        }
    }

}
