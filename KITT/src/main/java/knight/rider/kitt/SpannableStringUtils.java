package knight.rider.kitt;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.ColorInt;

public class SpannableStringUtils {

    /**
     * 特定字符串使用特殊颜色，限制首次出现特定字符串的位置才使用特殊颜色
     */
    public static SpannableStringBuilder equalsSpecialColor(String original, String special, @ColorInt int specialColor) {

        SpannableStringBuilder spannable = new SpannableStringBuilder(original);

        if (original == null || special == null || original.length() < special.length() || !original.contains(special))
            return spannable;

        // 设置字体颜色
        ForegroundColorSpan specialColorSpan = new ForegroundColorSpan(specialColor);
        spannable.setSpan(specialColorSpan, original.indexOf(special), original.indexOf(special) + special.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 特定字符串使用特殊颜色，所有出现特定字符串的位置都使用特殊颜色
     */
    public static SpannableStringBuilder equalsSpecialAllColor(String original, String special, @ColorInt int specialColor) {

        SpannableStringBuilder spannable = new SpannableStringBuilder(original);

        if (original == null || special == null || original.length() < special.length() || !original.contains(special))
            return spannable;

        int forCount = original.length() - special.length();

        for (int i = 0; i <= forCount; i++) {
            int i1 = original.indexOf(special, i);
            // 设置字体颜色
            ForegroundColorSpan specialColorSpan = new ForegroundColorSpan(specialColor);
            spannable.setSpan(specialColorSpan, i1, i1 + special.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            i += special.length();
            if (i > forCount)
                break;
        }

        return spannable;
    }
}
