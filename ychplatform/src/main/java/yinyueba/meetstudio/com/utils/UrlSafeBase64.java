package yinyueba.meetstudio.com.utils;

/**
 * 作者：chaoyongzhang on 15/11/12 09:12
 * 邮箱：zhangcy@meet-future.com
 */
import android.util.Base64;
import java.io.UnsupportedEncodingException;

public final class UrlSafeBase64 {
    public UrlSafeBase64() {
    }

    public static String encodeToString(String data) {
        try {
            return encodeToString(data.getBytes("utf-8"));
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String encodeToString(byte[] data) {
        return Base64.encodeToString(data, 10);
    }

    public static byte[] decode(String data) {
        return Base64.decode(data, 10);
    }
}