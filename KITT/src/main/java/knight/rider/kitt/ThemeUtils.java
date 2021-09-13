package knight.rider.kitt;

import android.content.Context;

public class ThemeUtils {

    /**
     * 系统是否是暗黑模式
     */
    public static boolean systemDarkMode(Context context) {
        return context.getResources().getConfiguration().uiMode == 0x21;
    }
}
