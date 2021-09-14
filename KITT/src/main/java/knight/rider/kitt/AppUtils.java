package knight.rider.kitt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresPermission;
import androidx.core.content.FileProvider;

import java.io.File;
import java.lang.reflect.Method;

import knight.rider.kitt.bean.AppCacheInfo;
import knight.rider.kitt.listener.AppCacheListener;

public class AppUtils {

    private static final String TAG = AppUtils.class.getName();

    private static final String SCHEME = "package";

    /**
     * 获取本应用缓存大小
     *
     * @param activity {@link android.app.Activity} object.
     * @param listener cache size callBack.
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {Manifest.permission.QUERY_ALL_PACKAGES, Manifest.permission.GET_PACKAGE_SIZE})
    public static void getAppCache(final Activity activity, final AppCacheListener listener) {
        getAppCache(activity, activity.getPackageName(), listener);
    }


    /**
     * 获取其它应用缓存大小
     * <p>
     * API26 需申请特殊权限: android.permission.PACKAGE_USAGE_STATS
     * <p>
     * 可使用工具类{@link PermissionPackageUsageStatsUtils}进行权限操作
     *
     * @param activity    {@link android.app.Activity} object.
     * @param packageName target app packageName.
     * @param listener    cache size callBack.
     */
    @RequiresPermission(allOf = {Manifest.permission.PACKAGE_USAGE_STATS, Manifest.permission.QUERY_ALL_PACKAGES, Manifest.permission.GET_PACKAGE_SIZE})
    public static void getAppCache(final Activity activity, final String packageName, final AppCacheListener listener) {

        if (null == activity) {
            Log.e(TAG, "activity不能为空");
            listener.getCacheError("activity为空");
            return;
        }

        if (activity.isFinishing()) {
            Log.e(TAG, "页面销毁，取消获取缓存大小");
            return;
        }

        if (TextUtils.isEmpty(packageName)) {
            Log.e(TAG, "包名不能为空");
            listener.getCacheError("包名为空");
            return;
        }


        if (listener == null)
            throw new RuntimeException("AppCacheListener不能为null");

        // 获取包管理器
        final PackageManager pm = activity.getPackageManager();
        ApplicationInfo pkgInfo = null;

        try {
            pkgInfo = pm.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "method：getAppCache()", e);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            try {
                Method method = pm.getClass().getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
                final ApplicationInfo finalPkgInfo = pkgInfo;

                method.invoke(pm, packageName, new IPackageStatsObserver.Stub() {
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) {

                        // 子线程

                        try {

                            Looper.prepare();

                            //获取缓存
                            long cache = pStats.cacheSize;
                            String appName = finalPkgInfo.loadLabel(pm).toString();

                            final AppCacheInfo appCacheInfo = new AppCacheInfo(packageName, appName, cache, finalPkgInfo.loadIcon(pm));

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.getCache(appCacheInfo);
                                }
                            });

                        } catch (final Exception e) {
                            Log.e(TAG, "method：getAppCache() < Api26", e);

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.getCacheError(e.getMessage());
                                }
                            });
                        }
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, "method：getAppCache() < Api26", e);
                listener.getCacheError(e.getMessage());
            }
        } else {

            if (pkgInfo == null) {
                listener.getCacheError("未找到包名对应的应用");
                return;
            }

            if (!PermissionPackageUsageStatsUtils.checkPackageUsageStatsPermission(activity) && !packageName.equals(activity.getPackageName())) {
                Log.e(TAG, "大于API26，请先调用PermissionUtils.requestPackageUsageStatsPermission()获取权限再调用获取缓存方法");
                listener.getCacheError("缺少权限: android.permission.PACKAGE_USAGE_STATS");
                return;
            }

            @SuppressLint("WrongConstant") final StorageStatsManager storageStatsManager = (StorageStatsManager) activity.getSystemService(Context.STORAGE_STATS_SERVICE);

            try {
                ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(packageName, 0);
                StorageStats storageStats = storageStatsManager.queryStatsForUid(ai.storageUuid, pkgInfo.uid);
                long cacheBytes = storageStats.getCacheBytes();

                String appName = pkgInfo.loadLabel(pm).toString();

                AppCacheInfo appCacheInfo = new AppCacheInfo(packageName, appName, cacheBytes, pkgInfo.loadIcon(pm));

                listener.getCache(appCacheInfo);
            } catch (Exception e) {
                Log.e(TAG, "method：getAppCache() > Api26", e);
                listener.getCacheError(e.getMessage());
            }
        }
    }

    /**
     * 启动本应用信息页面
     *
     * @param activity {@link android.app.Activity} object.
     */
    public static void startAppInfoActivity(Activity activity) {
        startAppInfoActivity(activity, activity.getPackageName());
    }

    /**
     * 启动其它应用信息页面
     *
     * @param activity    {@link android.app.Activity} object.
     * @param packageName target app packageName.
     */
    public static void startAppInfoActivity(Context activity, String packageName) {
        try {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "method：startAppInfoActivity()", e);
        }
    }


    /**
     * 安装APK
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param context     The context to use.  Usually your {@link android.app.Application}
     *                    or {@link android.app.Activity} object.
     * @param authorities The authority of a {@link FileProvider} defined in a
     *                    {@code <provider>} element in your app's manifest.
     * @param filePath    The install apk file absolutePath.
     */
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES})
    public static void installApk(Context context, String authorities, String filePath) {
        installApk(context, authorities, new File(filePath));
    }

    /**
     * 安装APK
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param context     The context to use.  Usually your {@link android.app.Application}
     *                    or {@link android.app.Activity} object.
     * @param authorities The authority of a {@link FileProvider} defined in a
     *                    {@code <provider>} element in your app's manifest.
     * @param file        The install apk file.
     */
    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES})
    public static void installApk(Context context, String authorities, File file) {

        Intent appIntent = new Intent(Intent.ACTION_VIEW);

        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, authorities, file);
            appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            appIntent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {
            appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        context.startActivity(appIntent);
    }

    /**
     * 是否已经安装了应用
     *
     * @param packageName target app packageName.
     */
    public static boolean isAppInstalled(Context context, String packageName) {

        PackageManager packageManager = context.getPackageManager();
        boolean hasInstall;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_GIDS);
            hasInstall = packageInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            hasInstall = false;
            Log.e(TAG, "isApkInstalled()", e);
        }
        return hasInstall;

    }

    /**
     * 获取本应用版本号
     *
     * @param activity {@link android.app.Activity} object.
     */
    public static int getAppVersionCode(Context activity) {
        int version_code = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            version_code = activity.getPackageManager().
                    getPackageInfo(activity.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version_code;
    }

    /**
     * 获取其它应用版本号
     *
     * @param activity    {@link android.app.Activity} object.
     * @param packageName target app packageName.
     */
    public static int getAppVersionCode(Context activity, String packageName) {
        int version_code = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            version_code = activity.getPackageManager().
                    getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version_code;
    }

    /**
     * 获取本应用版本名称
     *
     * @param activity {@link android.app.Activity} object.
     */
    public static String getAppVersionName(Context activity) {
        String version_name = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            version_name = activity.getPackageManager().
                    getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version_name;
    }

    /**
     * 获取其它应用版本名称
     *
     * @param activity    {@link android.app.Activity} object.
     * @param packageName target app packageName.
     */
    public static String getAppVersionName(Context activity, String packageName) {
        String version_name = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            version_name = activity.getPackageManager().
                    getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version_name;
    }

    /**
     * 结束当前App进程
     */
    public static void killProgress() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 重启App
     */
    public static void rebootApp(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//与正常页面跳转一样可传递序列化数据,在Launch页面内获得
        context.startActivity(intent);
        killProgress();
    }

    /**
     * 重启App
     */
    public static void rebootAppByAlarm(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, restartIntent);
        killProgress();
    }
}
