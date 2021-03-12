package knight.rider.kitt.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;

/**
 * 应用信息实体类
 */
public class AppCacheInfo {

    private String packageName;

    private String name;
    private long cache;
    private Drawable icon;

    public AppCacheInfo(String packageName, String name, long cache, Drawable icon) {
        this.packageName = packageName;
        this.name = name;

        this.cache = cache;
        this.icon = icon;
    }

    public AppCacheInfo(String packageName, String name, Drawable icon) {
        this.packageName = packageName;
        this.name = name;
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCache() {
        return cache;
    }

    public void setCache(long cache) {
        this.cache = cache;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getCacheStr(Context context) {
        return Formatter.formatFileSize(context, cache);
    }

    @Override
    public String toString() {
        return "AppCacheInfo{" +
                "packageName='" + packageName + '\'' +
                ", name='" + name + '\'' +
                ", cache=" + cache +
                ", icon=" + icon +
                '}';
    }
}
