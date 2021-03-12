# AppUtils


- getAppCache() 获取手机应用缓存

注意： Build.VERSION.SDK_INT >= Build.VERSION_CODES.O 需要获取权限: android.permission.PACKAGE_USAGE_STATS

- startAppInfoActivity() 启动应用信息页面

- installApk() 安装Apk

注意：其中一参数为临时授权的唯一标识（同清单文件一致）

- startAppInfoActivity() 跳转应用信息页面