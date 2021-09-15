package knight.rider.kitt;

import static android.Manifest.permission.USE_FINGERPRINT;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;

public class FingerprintUtils {


    /**
     * 是否支持指纹,必须设置锁屏以及添加指纹
     */
    @RequiresPermission(USE_FINGERPRINT)
    public static boolean supportFingerprint(Context context) {

        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            KeyguardManager keyguardManager = context.getSystemService(KeyguardManager.class);
            FingerprintManager fingerprintManager = context.getSystemService(FingerprintManager.class);
            if (fingerprintManager == null) {
                return false;
            } else if (!fingerprintManager.isHardwareDetected()) {
                return false;
            } else if (!keyguardManager.isKeyguardSecure()) {
                return false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否支持指纹,必须设置锁屏以及添加指纹，如果不支持弹出Toast
     */
    @RequiresPermission(USE_FINGERPRINT)
    public static boolean supportFingerprintByToast(Context context) {

        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(context, "您的系统版本过低，不支持指纹功能", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            KeyguardManager keyguardManager = context.getSystemService(KeyguardManager.class);
            FingerprintManager fingerprintManager = context.getSystemService(FingerprintManager.class);
            if (fingerprintManager == null) {
                Toast.makeText(context, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(context, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(context, "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(context, "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
