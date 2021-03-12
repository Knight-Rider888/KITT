package knight.rider.kitt;

import android.Manifest;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class FileUtils {

    /**
     * Bitmap写入到指定文件
     * <p>
     * Android 6.0 非分区存储目录下存储文件需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略权限提示
     * <p>
     * ******流读写完毕 共享文件夹需扫描媒体中心******
     * <p>
     * {@link knight.rider.kitt.mime.MediaFile}.scanMediaCenter()
     * <p>
     * ******分区存储操作无需扫描媒体中心*************
     *
     * @param bitmap   The input image.
     * @param filePath The output file absolutePath.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static void writeBitmapToFile(Bitmap bitmap, String filePath) {

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(filePath));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            Log.e(FileUtils.class.getSimpleName(), "writeBitmapToFile()", e);
        }
    }

}
