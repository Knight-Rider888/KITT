package knight.rider.kitt.http;

import android.util.Base64;

public class HttpAuthorizationHeader {


    /**
     * 组装header的基础认证
     *
     * @param user The login user name.
     * @param psd  The login user password.
     * @return Basic + base64
     */
    public static String getBasicAuthorization(String user, String psd) {
        String credentials = user + ":" + psd;
        return "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }


    /**
     * 组装header令牌认证
     *
     * @param accessToken The login user token.
     * @return Bearer + accessToken
     */
    public static String getBeaterAuthorization(String accessToken) {
        return "Bearer " + accessToken;
    }
}
