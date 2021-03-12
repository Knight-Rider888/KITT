package knight.rider.kitt;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5Utils {

    private static final String TAG = MD5Utils.class.getName();

    /**
     * 获取文件的MD5值
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param filePath absolutePath for the file to be encrypted.
     */
    public static String getFileMD5(String filePath) {
        return getFileMD5(new File(filePath));
    }

    /**
     * 获取文件的MD5值
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param file the file to be encrypted.
     */
    public static String getFileMD5(File file) {

        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream in = null;

        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
            return bytesToHexString(digest.digest());
        } catch (Exception e) {
            Log.e(TAG, "method：getFileMD5()", e);
        }

        return null;
    }

    private static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder("");

        if (src == null || src.length <= 0) {
            return null;
        }

        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
