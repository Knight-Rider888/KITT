package knight.rider.kitt.listener;

import knight.rider.kitt.bean.AppCacheInfo;

public interface AppCacheListener {

    void getCache(AppCacheInfo appCacheInfo);

    void getCacheError(String err);
}
