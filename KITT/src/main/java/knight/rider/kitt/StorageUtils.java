package knight.rider.kitt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Objects;

import knight.rider.kitt.mime.MediaFile;
import knight.rider.kitt.type.ShareStorage;


public class StorageUtils {


    /**
     * 要返回的分区存储的根目录
     */
    public static String getExclusiveRootStorageDir(Context context) {
        return getExclusiveStorageDir(context, null);
    }

    /**
     * 要返回的分区存储的目录，可以是分区存储的根目录或子目录
     *
     * @param subDirRelativePath May be {@code null} for the root of the files directory
     *                           or one of the following onstants for a subdirectory.
     */
    public static String getExclusiveStorageDir(Context context, String subDirRelativePath) {
        String absolutePath = Objects.requireNonNull(context.getExternalFilesDir(subDirRelativePath)).getAbsolutePath();
        new File(absolutePath).mkdirs();
        return absolutePath;
    }

    /**
     * 要返回的共享文件的根目录
     * <p>
     * Android 6.0 需动态申请权限：Manifest.permission.WRITE_EXTERNAL_STORAGE
     *
     * @param storage witch root directory.
     */
    public static String getShareRootStorageDir(ShareStorage storage) {
        String absolutePath = Environment.getExternalStoragePublicDirectory(storage.getDir()).getAbsolutePath();
        new File(absolutePath).mkdirs();
        return absolutePath;
    }

    /**
     * 要返回的共享文件的目录，可以是{@link knight.rider.kitt.type.ShareStorage}的根目录或子目录
     * <p>
     * Android 6.0 需动态申请权限：Manifest.permission.WRITE_EXTERNAL_STORAGE
     *
     * @param storage            witch root directory.
     * @param subDirRelativePath May be {@code null} for the root of the files directory
     *                           or one of the following onstants for a subdirectory.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static String getShareStorageDir(ShareStorage storage, String subDirRelativePath) {
        if (TextUtils.isEmpty(subDirRelativePath) || TextUtils.isEmpty(subDirRelativePath.trim()) || (subDirRelativePath.startsWith(File.separator) && subDirRelativePath.trim().length() == 1))
            return getShareRootStorageDir(storage);
        else {
            String absolutePath = getShareRootStorageDir(storage) + (subDirRelativePath.startsWith(File.separator) ? subDirRelativePath.trim() : (File.separator + subDirRelativePath.trim()));
            new File(absolutePath).mkdirs();
            return absolutePath;
        }
    }

    /**
     * 获取专属分区文件地址，如果文件已存在，返回重命名后的地址
     *
     * @param subDirRelativePath May be {@code null} for the root of the files directory
     *                           or one of the following onstants for a subdirectory.
     * @param fileName           May be contain file extension or no file extension.
     * @param mimeType           The MIME type of the data.
     */
    @SuppressLint("MissingPermission")
    public static String getExclusiveFilePath(Context context, String subDirRelativePath, @NonNull String fileName, MediaFile.MediaFileType mimeType) {

        // 根目录
        String rootFilePath = getExclusiveStorageDir(context, subDirRelativePath);

        // 去除已知的扩展名，然后根据已知扩展名进行填充
        fileName = MediaFile.getFileTitleByKnownMimeType(fileName) + (mimeType == null ? "" : "." + mimeType.extension.toLowerCase());


        // 需要判断名称是否重复，重复重命名
        fileName = localFileReleaseNames(rootFilePath, fileName);

        return rootFilePath + File.separator + fileName;
    }


    /**
     * 获取共享媒体资源的文件地址，根据 MediaFileType 会自动选择适合的文件目录，如果文件已存在，返回重命名后的地址
     * <p>
     * Android 6.0 需动态申请权限：Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * ******流读写完毕 需清扫描媒体中心******
     * <p>
     * {@link knight.rider.kitt.mime.MediaFile}.scanMediaCenter()
     * <p>
     * ***********************************
     *
     * @param subDirRelativePath May be {@code null} for the root of the files directory
     *                           or one of the following onstants for a subdirectory.
     * @param fileName           May be contain file extension or no file extension.
     * @param mimeType           The MIME type of the data.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static String getShareFilePath(String subDirRelativePath, @NonNull String fileName, MediaFile.MediaFileType mimeType) {

        // 根文件夹的目录
        String rootFilePath;

        if (mimeType == null) {
            // 未知类型
            rootFilePath = getShareStorageDir(ShareStorage.DIRECTORY_DOWNLOADS, subDirRelativePath);
        } else if (MediaFile.isVideoFileType(mimeType.fileType)) {
            // 视频类型
            rootFilePath = getShareStorageDir(ShareStorage.DIRECTORY_MOVIES, subDirRelativePath);
        } else if (MediaFile.isImageFileType(mimeType.fileType)) {
            // 图片类型
            rootFilePath = getShareStorageDir(ShareStorage.DIRECTORY_PICTURES, subDirRelativePath);
        } else if (MediaFile.isAudioFileType(mimeType.fileType)) {
            // 音频类型
            rootFilePath = getShareStorageDir(ShareStorage.DIRECTORY_MUSIC, subDirRelativePath);
        } else {
            // 其它类型
            rootFilePath = getShareStorageDir(ShareStorage.DIRECTORY_DOWNLOADS, subDirRelativePath);
        }


        // 去除已知的扩展名，然后根据已知扩展名进行填充
        fileName = MediaFile.getFileTitleByKnownMimeType(fileName) + (mimeType == null ? "" : "." + mimeType.extension.toLowerCase());

        // 需要判断名称是否重复，重复重命名
        fileName = localFileReleaseNames(rootFilePath, fileName);


        boolean isOpenSuccess = true;

        FileOutputStream fileOutputStream = null;

        try {
            // OPPO 如果已删除文件与要写入文件同名 打开输出流会失败，华为则无问题
            // 减少异常出现的几率，打开失败时前面加入时间戳
            fileOutputStream = new FileOutputStream(new File(rootFilePath + File.separator + fileName));
            if (fileOutputStream != null)
                fileOutputStream.close();
        } catch (Exception e) {
            isOpenSuccess = false;
        }

        return rootFilePath + File.separator + (isOpenSuccess ? fileName : System.currentTimeMillis() + fileName);
    }


    /**
     * 如果文件已存在，返回新的重命名后的文件地址
     * 否则直接返回文件地址
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param directory The directory where this file is located.
     * @param fileName  must be contain file extension.
     * @return
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    private static String localFileReleaseNames(File directory, String fileName) {
        return localFileReleaseNames(directory.getAbsolutePath(), fileName);
    }

    /**
     * 如果文件已存在，返回新的重命名后的文件地址
     * 否则直接返回文件地址
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param directory The directory where this file is located.
     * @param fileName  must be contain file extension.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    private static String localFileReleaseNames(String directory, String fileName) {

        File f = new File(directory);

        String[] fileSplit = fileName.split("\\.");

        String fileNameStr = fileSplit.length > 1 ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
        String fileTypeStr = fileSplit.length > 1 ? "." + fileSplit[fileSplit.length - 1] : "";

        if (f.exists()) {//判断路径是否存在
            File[] files = f.listFiles();
            // 存入所有已存在文件
            HashSet<String> hashSet = new HashSet<>();
            assert files != null;
            for (File file : files) {
                if (file.isFile()) {

                    String[] split = file.getAbsolutePath().split(File.separator);
                    String name = split[split.length - 1];
                    hashSet.add(name);
                }
            }

            int a = 0;
            while (true) {

                if (a == 0) {

                    if (!hashSet.contains(fileName)) {
                        break;
                    }
                } else {

                    if (!hashSet.contains(fileNameStr + "(" + a + ")" + fileTypeStr)) {
                        fileName = fileNameStr + "(" + a + ")" + fileTypeStr;
                        break;
                    }
                }

                a++;
            }
        }

        return fileName;
    }
}
