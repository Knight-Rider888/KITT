package knight.rider.kitt;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import knight.rider.kitt.type.TextPosition;

public class ImageUtils {

    /**
     * Bitmap去除白色并用透明替代
     */
    public static Bitmap imageEliminateWhite(Bitmap bitmap) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int mWidth = bitmap.getWidth();
            int mHeight = bitmap.getHeight();
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mWidth; j++) {
                    int color = bitmap.getPixel(j, i);
                    int g = Color.green(color);
                    int r = Color.red(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    if (g >= 235 && r >= 235 && b >= 235) {
                        a = 0;
                    }
                    color = Color.argb(a, r, g, b);
                    createBitmap.setPixel(j, i, color);
                }
            }
            return createBitmap;
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "imageEliminateWhite()", e);
        }
        return null;
    }

    /**
     * Bitmap去除黑色并用透明替代
     */
    public static Bitmap imageEliminateBlack(Bitmap bitmap) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int mWidth = bitmap.getWidth();
            int mHeight = bitmap.getHeight();
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mWidth; j++) {
                    int color = bitmap.getPixel(j, i);
                    int g = Color.green(color);
                    int r = Color.red(color);
                    int b = Color.blue(color);
                    int a = Color.alpha(color);
                    if (g <= 45 && r <= 45 && b <= 45) {
                        a = 0;
                    }
                    color = Color.argb(a, r, g, b);
                    createBitmap.setPixel(j, i, color);
                }
            }
            return createBitmap;
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "imageEliminateBlack()", e);
        }

        return null;
    }


    /**
     * Bitmap转换成指定颜色，透明除外
     */
    public static Bitmap imageConvertColor(Bitmap bitmap, int targetColor) {
        try {

            int replaceG = Color.green(targetColor);
            int replaceR = Color.red(targetColor);
            int replaceB = Color.blue(targetColor);

            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int mWidth = bitmap.getWidth();
            int mHeight = bitmap.getHeight();
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mWidth; j++) {
                    int color = bitmap.getPixel(j, i);
                    int a = Color.alpha(color);

                    if (a != 0) {
                        color = Color.argb(a, replaceR, replaceG, replaceB);
                    }
                    createBitmap.setPixel(j, i, color);
                }
            }

            return createBitmap;
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "imageSaveRedPath()", e);
        }
        return null;
    }


    /**
     * 提高Bitmap亮度
     */
    public static Bitmap setImageHighlight(Bitmap bitmap) {

        try {
            ColorMatrix matrix = new ColorMatrix(new float[]{
                    1.2f, 0, 0, 0, 0,
                    0, 1.2f, 0, 0, 0,
                    0, 0, 1.2f, 0, 0,
                    0, 0, 0, 1.2f, 0,
            });


            Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmp);
            Paint paint = new Paint();
            paint.setColorFilter(new ColorMatrixColorFilter(matrix));
            canvas.drawBitmap(bitmap, 0, 0, paint);
            return bmp;
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "setImageHighlight()", e);
        }
        return null;
    }

    /**
     * 图片通过尺寸缩放，达到指定存储大小
     * <p>
     * 当压缩到极小可能出现误差（20KB），会大10%左右
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param imagePath complete path for the file to be scaled.
     * @param maxKb     max size of Image.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static Bitmap scaleToTargetKB(String imagePath, int maxKb) {

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            // 指定尺寸宽*高
            int targetSize = 1024 * maxKb / 4;

            double scale = new File(imagePath).length() * 1.0 / targetSize;

            double inSampleSize = 1;

            if (scale > 1) {
                // 需要缩放的比例
                inSampleSize = Math.sqrt(scale);
            }

            options.inSampleSize = (int) Math.ceil(inSampleSize);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "scaleToTargetKB()", e);
        }

        return null;
    }


    /**
     * 图片通过质量压缩，达到指定存储大小(透明度无法保存)
     * <p>
     * Android 6.0 非分区存储目录操作需动态申请权限：
     * <p>
     * Manifest.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * 分区存储操作请忽略提示
     *
     * @param savePath compressed picture save location.
     * @param maxKb    max size of Image.
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static void compressToTargetKB(Bitmap bitmap, String savePath, int maxKb) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
            int quality = 95;
            boolean flag = true;
            while (bs.toByteArray().length / 1000 > maxKb && flag) {
                bs.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bs);
                quality -= 5;
                if (quality <= 0) {
                    flag = false;
                }
            }
            FileOutputStream fos = new FileOutputStream(new File(savePath));
            fos.write(bs.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e(ImageUtils.class.getName(), "compressBmpToFile()", e);
        }
    }


    /**
     * 逐行扫描，移除指定颜色的边界区域，并更改bitmap尺寸
     */
    public static Bitmap removeBoundaryByColor(Bitmap bp, int removeBoundaryColor) {
        try {
            int HEIGHT = bp.getHeight();
            int WIDTH = bp.getWidth();
            int top = 0, left = 0, right = 0, bottom = 0;
            int[] pixs = new int[WIDTH];
            boolean isStop;
            for (int y = 0; y < HEIGHT; y++) {
                bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
                isStop = false;
                for (int pix : pixs) {
                    if (pix != removeBoundaryColor) {
                        top = y;
                        isStop = true;
                        break;
                    }
                }
                if (isStop) {
                    break;
                }
            }
            for (int y = HEIGHT - 1; y >= 0; y--) {
                bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
                isStop = false;
                for (int pix : pixs) {
                    if (pix != removeBoundaryColor) {
                        bottom = y;
                        isStop = true;
                        break;
                    }
                }
                if (isStop) {
                    break;
                }
            }
            pixs = new int[HEIGHT];
            for (int x = 0; x < WIDTH; x++) {
                bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
                isStop = false;
                for (int pix : pixs) {
                    if (pix != removeBoundaryColor) {
                        left = x;
                        isStop = true;
                        break;
                    }
                }
                if (isStop) {
                    break;
                }
            }
            for (int x = WIDTH - 1; x > 0; x--) {
                bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
                isStop = false;
                for (int pix : pixs) {
                    if (pix != removeBoundaryColor) {
                        right = x;
                        isStop = true;
                        break;
                    }
                }
                if (isStop) {
                    break;
                }
            }

            left = Math.max(left, 0);
            top = Math.max(top, 0);
            right = Math.min(right, WIDTH - 1);
            bottom = Math.min(bottom, HEIGHT - 1);
            Bitmap.createBitmap(bp, left, top, (right - left), (bottom - top));
            return Bitmap.createBitmap(bp, left, top, (right - left), (bottom - top));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加水印文字
     *
     * @param waterText   the watermark content.
     * @param position    the text position ,support top and bottom.
     * @param textColor   the new color (including alpha) to set in the paint.
     * @param textSize    set the paint's text size in pixel units.
     * @param textBgColor set the text background color.
     * @param textBgAlpha set the alpha component [0..255] of the paint's color.
     */
    public static Bitmap addWatermark(Bitmap bitmap, String waterText, TextPosition position, @ColorInt int textColor, int textSize, @ColorInt int textBgColor, int textBgAlpha) {
        // 获取原始图片与水印图片的宽与高
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 向位图中开始画入Bitmap原始图片
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 添加文字
        TextPaint mPaint = new TextPaint();
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);

        // 行高
        float lineHeight = mPaint.descent() - mPaint.ascent();
        // 水印的位置坐标(设置最大行数，需要反射调用13参，暂不支持)
        StaticLayout layout = new StaticLayout(
                waterText, 0, waterText.length(), mPaint, bitmap.getWidth() - 40, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false, TextUtils.TruncateAt.END, bitmap.getWidth() - 40);

        int lineCount = layout.getLineCount();
        canvas.save();

        Paint p = new Paint();
        p.setColor(textBgColor);
        p.setAlpha(textBgAlpha);

        if (lineCount > bitmapHeight / lineHeight) {
            // 超出
            canvas.drawRect(0, 0, bitmapWidth, bitmapHeight, p);
            canvas.translate(20, 0);
        } else {

            float v = lineCount * lineHeight;

            if (position == TextPosition.TOP) {
                canvas.drawRect(0, 0, bitmapWidth, v, p);
                canvas.translate(20, 0);
            } else {
                canvas.drawRect(0, bitmapHeight - v, bitmapWidth, bitmapHeight, p);
                canvas.translate(20, bitmapHeight - v);
            }
        }


        layout.draw(canvas);
        canvas.restore();

        return newBitmap;
    }
}
