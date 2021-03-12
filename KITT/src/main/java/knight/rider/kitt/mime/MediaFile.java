package knight.rider.kitt.mime;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/**
 * TODO MIME TYPE
 */
public class MediaFile {

    // Audio file types
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_AAC = 8;
    public static final int FILE_TYPE_MKA = 9;
    public static final int FILE_TYPE_FLAC = 10;
    private static final int FIRST_AUDIO_FILE_TYPE = FILE_TYPE_MP3;
    private static final int LAST_AUDIO_FILE_TYPE = FILE_TYPE_FLAC;

    // MIDI file types
    public static final int FILE_TYPE_MID = 11;
    public static final int FILE_TYPE_SMF = 12;
    public static final int FILE_TYPE_IMY = 13;
    private static final int FIRST_MIDI_FILE_TYPE = FILE_TYPE_MID;
    private static final int LAST_MIDI_FILE_TYPE = FILE_TYPE_IMY;

    // Video file types
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_WMV = 25;
    public static final int FILE_TYPE_ASF = 26;
    public static final int FILE_TYPE_MKV = 27;
    public static final int FILE_TYPE_MP2TS = 28;
    public static final int FILE_TYPE_AVI = 29;
    public static final int FILE_TYPE_WEBM = 30;
    private static final int FIRST_VIDEO_FILE_TYPE = FILE_TYPE_MP4;
    private static final int LAST_VIDEO_FILE_TYPE = FILE_TYPE_WEBM;

    // More video file types
    public static final int FILE_TYPE_MP2PS = 200;
    public static final int FILE_TYPE_QT = 201;
    private static final int FIRST_VIDEO_FILE_TYPE2 = FILE_TYPE_MP2PS;
    private static final int LAST_VIDEO_FILE_TYPE2 = FILE_TYPE_QT;

    // Image file types
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_WBMP = 35;
    public static final int FILE_TYPE_WEBP = 36;
    public static final int FILE_TYPE_HEIF = 37;
    private static final int FIRST_IMAGE_FILE_TYPE = FILE_TYPE_JPEG;
    private static final int LAST_IMAGE_FILE_TYPE = FILE_TYPE_HEIF;

    // Raw image file types
    public static final int FILE_TYPE_DNG = 300;
    public static final int FILE_TYPE_CR2 = 301;
    public static final int FILE_TYPE_NEF = 302;
    public static final int FILE_TYPE_NRW = 303;
    public static final int FILE_TYPE_ARW = 304;
    public static final int FILE_TYPE_RW2 = 305;
    public static final int FILE_TYPE_ORF = 306;
    public static final int FILE_TYPE_RAF = 307;
    public static final int FILE_TYPE_PEF = 308;
    public static final int FILE_TYPE_SRW = 309;
    private static final int FIRST_RAW_IMAGE_FILE_TYPE = FILE_TYPE_DNG;
    private static final int LAST_RAW_IMAGE_FILE_TYPE = FILE_TYPE_SRW;

    // Playlist file types
    public static final int FILE_TYPE_M3U = 41;
    public static final int FILE_TYPE_PLS = 42;
    public static final int FILE_TYPE_WPL = 43;
    public static final int FILE_TYPE_HTTPLIVE = 44;

    private static final int FIRST_PLAYLIST_FILE_TYPE = FILE_TYPE_M3U;
    private static final int LAST_PLAYLIST_FILE_TYPE = FILE_TYPE_HTTPLIVE;

    // Drm file types
    public static final int FILE_TYPE_FL = 51;
    private static final int FIRST_DRM_FILE_TYPE = FILE_TYPE_FL;
    private static final int LAST_DRM_FILE_TYPE = FILE_TYPE_FL;


    // Excel file types
    public static final int FILE_TYPE_MS_EXCEL = 400;
    public static final int FILE_TYPE_MS_XLAM_EXCEL = 401;
    public static final int FILE_TYPE_MS_XLSB_EXCEL = 402;
    public static final int FILE_TYPE_MS_XLSM_EXCEL = 403;
    public static final int FILE_TYPE_MS_XLTM_EXCEL = 404;
    public static final int FILE_TYPE_MS_XLSX_EXCEL = 405;
    public static final int FILE_TYPE_MS_XLTX_EXCEL = 406;

    private static final int FIRST_EXCEL_FILE_TYPE = FILE_TYPE_MS_EXCEL;
    private static final int LAST_EXCEL_FILE_TYPE = FILE_TYPE_MS_XLTX_EXCEL;


    // Word file types
    public static final int FILE_TYPE_MS_WORD = 450;
    public static final int FILE_TYPE_MS_DOCM_WORD = 451;
    public static final int FILE_TYPE_MS_DOCX_WORD = 452;
    public static final int FILE_TYPE_MS_DOTM_WORD = 453;
    public static final int FILE_TYPE_MS_DOTX_WORD = 454;

    private static final int FIRST_WORD_FILE_TYPE = FILE_TYPE_MS_WORD;
    private static final int LAST_WORD_FILE_TYPE = FILE_TYPE_MS_DOTX_WORD;


    // Ppt file types
    public static final int FILE_TYPE_MS_POWERPOINT = 500;
    public static final int FILE_TYPE_MS_POTM_POWERPOINT = 501;
    public static final int FILE_TYPE_MS_POTX_POWERPOINT = 502;
    public static final int FILE_TYPE_MS_PPAM_POWERPOINT = 503;
    public static final int FILE_TYPE_MS_PPSM_POWERPOINT = 504;
    public static final int FILE_TYPE_MS_PPSX_POWERPOINT = 505;
    public static final int FILE_TYPE_MS_PPTM_POWERPOINT = 506;
    public static final int FILE_TYPE_MS_PPTX_POWERPOINT = 507;

    private static final int FIRST_PPT_FILE_TYPE = FILE_TYPE_MS_POWERPOINT;
    private static final int LAST_PPT_FILE_TYPE = FILE_TYPE_MS_PPTX_POWERPOINT;


    public static final int FILE_TYPE_ZIP = 700;
    public static final int FILE_TYPE_RAR = 701;
    public static final int FILE_TYPE_7Z = 702;
    public static final int FILE_TYPE_CAB = 703;
    public static final int FILE_TYPE_ACE = 704;
    public static final int FILE_TYPE_CFS = 705;
    public static final int FILE_TYPE_DGC = 706;
    public static final int FILE_TYPE_LHA = 707;
    public static final int FILE_TYPE_LZH = 708;
    public static final int FILE_TYPE_TAR = 709;
    public static final int FILE_TYPE_GZ = 710;
    public static final int FILE_TYPE_BZ = 711;
    public static final int FILE_TYPE_BZ2 = 712;
    public static final int FILE_TYPE_UVZ = 713;

    private static final int FIRST_ZIP_FILE_TYPE = FILE_TYPE_ZIP;
    private static final int LAST_ZIP_FILE_TYPE = FILE_TYPE_UVZ;


    // Other popular file types
    public static final int FILE_TYPE_TEXT = 100;
    public static final int FILE_TYPE_PDF = 102;

    public static final int FILE_TYPE_HTML = 101;
    public static final int FILE_TYPE_XML = 103;

    public static final int FILE_TYPE_APP = 104;


    public static class MediaFileType {
        public final int fileType;
        public final String mimeType;
        public final String extension;

        MediaFileType(int fileType, String mimeType, String extension) {
            this.fileType = fileType;
            this.mimeType = mimeType;
            this.extension = extension;
        }

        // 可是网络地址，本地文件地址以及文件名(包含扩展名)
        // 必须包含. 否则不能知道扩展名是什么
        public static MediaFileType getMediaType(String pathOrFileName) {
            return getFileType(pathOrFileName);
        }
    }

    private static final HashMap<String, MediaFileType> sFileTypeMap
            = new HashMap<String, MediaFileType>();
    private static final HashMap<String, Integer> sMimeTypeMap
            = new HashMap<String, Integer>();

    static void addFileType(String extension, int fileType, String mimeType) {
        sFileTypeMap.put(extension, new MediaFileType(fileType, mimeType, extension));
        sMimeTypeMap.put(mimeType, Integer.valueOf(fileType));
    }


    static {
        addFileType("MP3", FILE_TYPE_MP3, "audio/mpeg");
        addFileType("MPGA", FILE_TYPE_MP3, "audio/mpeg");
        addFileType("M4A", FILE_TYPE_M4A, "audio/mp4");
        addFileType("WAV", FILE_TYPE_WAV, "audio/x-wav");
        addFileType("AMR", FILE_TYPE_AMR, "audio/amr");
        addFileType("AWB", FILE_TYPE_AWB, "audio/amr-wb");
        addFileType("WMA", FILE_TYPE_WMA, "audio/x-ms-wma");
        addFileType("OGG", FILE_TYPE_OGG, "audio/ogg");
        addFileType("OGG", FILE_TYPE_OGG, "application/ogg");
        addFileType("OGA", FILE_TYPE_OGG, "application/ogg");
        addFileType("AAC", FILE_TYPE_AAC, "audio/aac");
        addFileType("AAC", FILE_TYPE_AAC, "audio/aac-adts");
        addFileType("MKA", FILE_TYPE_MKA, "audio/x-matroska");

        addFileType("MID", FILE_TYPE_MID, "audio/midi");
        addFileType("MIDI", FILE_TYPE_MID, "audio/midi");
        addFileType("XMF", FILE_TYPE_MID, "audio/midi");
        addFileType("RTTTL", FILE_TYPE_MID, "audio/midi");
        addFileType("SMF", FILE_TYPE_SMF, "audio/sp-midi");
        addFileType("IMY", FILE_TYPE_IMY, "audio/imelody");
        addFileType("RTX", FILE_TYPE_MID, "audio/midi");
        addFileType("OTA", FILE_TYPE_MID, "audio/midi");
        addFileType("MXMF", FILE_TYPE_MID, "audio/midi");

        addFileType("MPEG", FILE_TYPE_MP4, "video/mpeg");
        addFileType("MPG", FILE_TYPE_MP4, "video/mpeg");
        addFileType("MP4", FILE_TYPE_MP4, "video/mp4");
        addFileType("M4V", FILE_TYPE_M4V, "video/mp4");
        addFileType("MOV", FILE_TYPE_QT, "video/quicktime");

        addFileType("3GP", FILE_TYPE_3GPP, "video/3gpp");
        addFileType("3GPP", FILE_TYPE_3GPP, "video/3gpp");
        addFileType("3G2", FILE_TYPE_3GPP2, "video/3gpp2");
        addFileType("3GPP2", FILE_TYPE_3GPP2, "video/3gpp2");
        addFileType("MKV", FILE_TYPE_MKV, "video/x-matroska");
        addFileType("WEBM", FILE_TYPE_WEBM, "video/webm");
        addFileType("TS", FILE_TYPE_MP2TS, "video/mp2ts");
        addFileType("AVI", FILE_TYPE_AVI, "video/avi");


        addFileType("WMV", FILE_TYPE_WMV, "video/x-ms-wmv");
        addFileType("ASF", FILE_TYPE_ASF, "video/x-ms-asf");


        addFileType("JPG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("JPEG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("GIF", FILE_TYPE_GIF, "image/gif");
        addFileType("PNG", FILE_TYPE_PNG, "image/png");
        addFileType("BMP", FILE_TYPE_BMP, "image/x-ms-bmp");
        addFileType("WBMP", FILE_TYPE_WBMP, "image/vnd.wap.wbmp");
        addFileType("WEBP", FILE_TYPE_WEBP, "image/webp");
        addFileType("HEIC", FILE_TYPE_HEIF, "image/heif");
        addFileType("HEIF", FILE_TYPE_HEIF, "image/heif");

        addFileType("DNG", FILE_TYPE_DNG, "image/x-adobe-dng");
        addFileType("CR2", FILE_TYPE_CR2, "image/x-canon-cr2");
        addFileType("NEF", FILE_TYPE_NEF, "image/x-nikon-nef");
        addFileType("NRW", FILE_TYPE_NRW, "image/x-nikon-nrw");
        addFileType("ARW", FILE_TYPE_ARW, "image/x-sony-arw");
        addFileType("RW2", FILE_TYPE_RW2, "image/x-panasonic-rw2");
        addFileType("ORF", FILE_TYPE_ORF, "image/x-olympus-orf");
        addFileType("RAF", FILE_TYPE_RAF, "image/x-fuji-raf");
        addFileType("PEF", FILE_TYPE_PEF, "image/x-pentax-pef");
        addFileType("SRW", FILE_TYPE_SRW, "image/x-samsung-srw");

        addFileType("M3U", FILE_TYPE_M3U, "audio/x-mpegurl");
        addFileType("M3U", FILE_TYPE_M3U, "application/x-mpegurl");
        addFileType("PLS", FILE_TYPE_PLS, "audio/x-scpls");
        addFileType("WPL", FILE_TYPE_WPL, "application/vnd.ms-wpl");
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "application/vnd.apple.mpegurl");
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "audio/mpegurl");
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "audio/x-mpegurl");

        addFileType("FL", FILE_TYPE_FL, "application/x-android-drm-fl");


        addFileType("XLA", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLC", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLM", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLS", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLT", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLW", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLAM", FILE_TYPE_MS_XLAM_EXCEL, "application/vnd.ms-excel.addin.macroenabled.12");
        addFileType("XLSB", FILE_TYPE_MS_XLSB_EXCEL, "application/vnd.ms-excel.sheet.binary.macroenabled.12");
        addFileType("XLSM", FILE_TYPE_MS_XLSM_EXCEL, "application/vnd.ms-excel.sheet.macroenabled.12");
        addFileType("XLTM", FILE_TYPE_MS_XLTM_EXCEL, "application/vnd.ms-excel.template.macroenabled.12");
        addFileType("XLSX", FILE_TYPE_MS_XLSX_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        addFileType("XLTX", FILE_TYPE_MS_XLTX_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.template");


        addFileType("DOC", FILE_TYPE_MS_WORD, "application/msword");
        addFileType("DOT", FILE_TYPE_MS_WORD, "application/msword");
        addFileType("DOCM", FILE_TYPE_MS_DOCM_WORD, "application/vnd.ms-word.document.macroenabled.12");
        addFileType("DOCX", FILE_TYPE_MS_DOCX_WORD, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        addFileType("DOTM", FILE_TYPE_MS_DOTM_WORD, "application/vnd.ms-word.template.macroenabled.12");
        addFileType("DOTX", FILE_TYPE_MS_DOTX_WORD, "application/vnd.openxmlformats-officedocument.wordprocessingml.template");


        addFileType("PPT", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint");
        addFileType("PPS", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint");
        addFileType("POT", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint");
        addFileType("POTM", FILE_TYPE_MS_POTM_POWERPOINT, "application/vnd.ms-powerpoint.template.macroenabled.12");
        addFileType("POTX", FILE_TYPE_MS_POTX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.template");
        addFileType("PPAM", FILE_TYPE_MS_PPAM_POWERPOINT, "application/vnd.ms-powerpoint.addin.macroenabled.12");
        addFileType("PPSM", FILE_TYPE_MS_PPSM_POWERPOINT, "application/vnd.ms-powerpoint.slideshow.macroenabled.12");
        addFileType("PPSX", FILE_TYPE_MS_PPSX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
        addFileType("PPTM", FILE_TYPE_MS_PPTM_POWERPOINT, "application/vnd.ms-powerpoint.presentation.macroenabled.12");
        addFileType("PPTX", FILE_TYPE_MS_PPTX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.presentation");


        addFileType("PDF", FILE_TYPE_PDF, "application/pdf");


        addFileType("RAR", FILE_TYPE_RAR, "application/x-rar-compressed");
        addFileType("ZIP", FILE_TYPE_ZIP, "application/zip");
        addFileType("7Z", FILE_TYPE_7Z, "application/x-7z-compressed");
        addFileType("ACE", FILE_TYPE_ACE, "application/x-ace-compressed");
        addFileType("CAB", FILE_TYPE_CAB, "application/vnd.ms-cab-compressed");
        addFileType("CFS", FILE_TYPE_CFS, "application/x-cfs-compressed");
        addFileType("DGC", FILE_TYPE_DGC, "aapplication/x-dgc-compressed");
        addFileType("LHA", FILE_TYPE_LHA, "application/x-lzh-compressed");
        addFileType("LZH", FILE_TYPE_LZH, "application/x-lzh-compressed");
        addFileType("TAR", FILE_TYPE_TAR, "application/x-tar");
        addFileType("GZ", FILE_TYPE_GZ, "application/x-gzip");
        addFileType("BZ", FILE_TYPE_BZ, "application/x-bzip");
        addFileType("BZ2", FILE_TYPE_BZ2, "application/x-bzip2");
        addFileType("UVZ", FILE_TYPE_UVZ, "application/vnd.dece.zip");


        addFileType("TXT", FILE_TYPE_TEXT, "text/plain");
        addFileType("HTM", FILE_TYPE_HTML, "text/html");
        addFileType("HTML", FILE_TYPE_HTML, "text/html");
        addFileType("APK", FILE_TYPE_APP, "application/vnd.android.package-archive");


        addFileType("FLAC", FILE_TYPE_FLAC, "audio/flac");

        addFileType("MPG", FILE_TYPE_MP2PS, "video/mp2p");
        addFileType("MPEG", FILE_TYPE_MP2PS, "video/mp2p");
    }

    /**
     * 是否是音频类型
     */
    public static boolean isAudioFileType(int fileType) {
        return ((fileType >= FIRST_AUDIO_FILE_TYPE &&
                fileType <= LAST_AUDIO_FILE_TYPE) ||
                (fileType >= FIRST_MIDI_FILE_TYPE &&
                        fileType <= LAST_MIDI_FILE_TYPE));
    }

    /**
     * 是否是视频类型
     */
    public static boolean isVideoFileType(int fileType) {
        return (fileType >= FIRST_VIDEO_FILE_TYPE &&
                fileType <= LAST_VIDEO_FILE_TYPE)
                || (fileType >= FIRST_VIDEO_FILE_TYPE2 &&
                fileType <= LAST_VIDEO_FILE_TYPE2);
    }


    /**
     * 是否是图片类型
     */
    public static boolean isImageFileType(int fileType) {
        return (fileType >= FIRST_IMAGE_FILE_TYPE &&
                fileType <= LAST_IMAGE_FILE_TYPE)
                || (fileType >= FIRST_RAW_IMAGE_FILE_TYPE &&
                fileType <= LAST_RAW_IMAGE_FILE_TYPE);
    }

    /**
     * 是否是原始图片类型
     */
    public static boolean isRawImageFileType(int fileType) {
        return (fileType >= FIRST_RAW_IMAGE_FILE_TYPE &&
                fileType <= LAST_RAW_IMAGE_FILE_TYPE);
    }


    /**
     * 是否是播放列表类型
     */
    public static boolean isPlayListFileType(int fileType) {
        return (fileType >= FIRST_PLAYLIST_FILE_TYPE &&
                fileType <= LAST_PLAYLIST_FILE_TYPE);
    }

    public static boolean isDrmFileType(int fileType) {
        return (fileType >= FIRST_DRM_FILE_TYPE &&
                fileType <= LAST_DRM_FILE_TYPE);
    }

    public static boolean isExcelFileType(int fileType) {
        return (fileType >= FIRST_EXCEL_FILE_TYPE &&
                fileType <= LAST_EXCEL_FILE_TYPE);
    }

    public static boolean isWordFileType(int fileType) {
        return (fileType >= FIRST_WORD_FILE_TYPE &&
                fileType <= LAST_WORD_FILE_TYPE);
    }

    public static boolean isPptFileType(int fileType) {
        return (fileType >= FIRST_PPT_FILE_TYPE &&
                fileType <= LAST_PPT_FILE_TYPE);
    }

    public static boolean isPdfFileType(int fileType) {
        return fileType == FILE_TYPE_PDF;
    }

    public static boolean isZipFileType(int fileType) {
        return (fileType >= FIRST_ZIP_FILE_TYPE &&
                fileType <= LAST_ZIP_FILE_TYPE);
    }

    public static boolean isTxtFileType(int fileType) {
        return fileType == FILE_TYPE_TEXT;
    }

    public static boolean isHtmlFileType(int fileType) {
        return fileType == FILE_TYPE_HTML;
    }

    /**
     * 是否是安装包类型
     */
    public static boolean isAppFileType(int fileType) {
        return fileType == FILE_TYPE_APP;
    }

    // 可是网络地址，本地文件地址以及文件名(包含扩展名)
    public static MediaFileType getFileType(String pathOrFileName) {
        int lastDot = pathOrFileName.lastIndexOf('.');
        if (lastDot < 0)
            return null;
        return sFileTypeMap.get(pathOrFileName.substring(lastDot + 1).toUpperCase(Locale.ROOT));
    }

    public static boolean isMimeTypeMedia(String mimeType) {
        int fileType = getFileTypeForMimeType(mimeType);
        return isAudioFileType(fileType) || isVideoFileType(fileType)
                || isImageFileType(fileType) || isPlayListFileType(fileType);
    }

    /**
     * 获取文件名
     */
    public static String getFileTitle(String path) {
        // 移除最后一个 / 前面的内容
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash >= 0) {
            lastSlash++;
            if (lastSlash < path.length()) {
                path = path.substring(lastSlash);
            }
        }
        // 最后一个点的index
        int lastDot = path.lastIndexOf('.');
        if (lastDot > 0) {
            path = path.substring(0, lastDot);
        }
        return path;
    }

    /**
     * 根据已知的MimeType获取文件名
     */
    public static String getFileTitleByKnownMimeType(String path) {
        // 移除最后一个 / 前面的内容
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash >= 0) {
            lastSlash++;
            if (lastSlash < path.length()) {
                path = path.substring(lastSlash);
            }
        }
        // 最后一个点的index
        int lastDot = path.lastIndexOf('.');
        String extension = "";
        String knowMimeTypeExtension = "";
        if (lastDot > 0) {

            if (lastDot < path.length())
                extension = path.substring(lastDot + 1);

            MediaFileType mediaType = MediaFileType.getMediaType(path);

            if (mediaType != null)
                knowMimeTypeExtension = mediaType.extension;

            if (!TextUtils.isEmpty(knowMimeTypeExtension) && knowMimeTypeExtension.equals(extension.toUpperCase(Locale.ROOT))) {
                path = path.substring(0, lastDot);
            }

        }
        return path;
    }

    /**
     * 获取Int类型的type
     */
    private static int getFileTypeForMimeType(String mimeType) {
        Integer value = sMimeTypeMap.get(mimeType);
        return (value == null ? 0 : value.intValue());
    }

    /**
     * 获取文件的MimeType类型
     */
    public static String getMimeTypeForFile(String path) {
        MediaFileType mediaFileType = getFileType(path);
        return (mediaFileType == null ? null : mediaFileType.mimeType);
    }

    /**
     * 获取文件的MimeType类型
     */
    public static String getMimeTypeForFile(File file) {
        MediaFileType mediaFileType = getFileType(file.getAbsolutePath());
        return (mediaFileType == null ? null : mediaFileType.mimeType);
    }

    /**
     * 扫描媒体库
     */
    public static void scanMediaCenter(Context context, String filePath) {
        Uri uri = Uri.fromFile(new File(filePath));
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(intent);
    }

    public static void scanMediaCenter(Context context, File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(intent);
    }

    public static void scanMediaCenter(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(intent);
    }
}
