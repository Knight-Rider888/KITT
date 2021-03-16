package knight.rider.kitt;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.RequiresPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Base64Utils {

    /**
     * 文件转Base64
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param file the file to be encoded.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static String file2Base64(File file) {
        return file2Base64(file.getAbsolutePath());
    }

    /**
     * 文件转Base64
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param filePath absolutePath for the file to be encoded.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static String file2Base64(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();

            return Base64.encodeToString(imgBytes, Base64.NO_WRAP);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data the input String to decode.
     */
    public static Bitmap base642Bitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 字符串转换成Base64
     *
     * @param input the data to encode
     */
    public static String string2Base64(String input) {
        if (null == input)
            return null;

        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * 字符串转换成Base64
     *
     * @param input   the data to encode
     * @param charset The {@linkplain java.nio.charset.Charset} to be used to encode
     *                the {@code String}
     */
    public static String string2Base64(String input, Charset charset) {
        if (null == input)
            return null;

        byte[] bytes = input.getBytes(charset);

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }
}
