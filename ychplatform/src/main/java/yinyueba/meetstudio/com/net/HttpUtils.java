package yinyueba.meetstudio.com.net;

import android.text.TextUtils;

import yinyueba.meetstudio.com.config.AppConstants;
import yinyueba.meetstudio.com.config.AppUtils;
import yinyueba.meetstudio.com.net.http.AsyncHttpClient;

/**
 * 作者：chaoyongzhang on 15/11/10 15:41
 * 邮箱：zhangcy@meet-future.com
 */
public class HttpUtils {
    public static AsyncHttpClient createClient(String url, String uid, String token){
        AsyncHttpClient c = new AsyncHttpClient();
        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(token)){
            c.addHeader("X-MEET-USER-ID", uid);
            c.addHeader("X-MEET-USER-TOKEN", token);
        }
        c.addHeader("X-MEET-API-VERSION", AppConstants.PF_REQUEST_API_VERSION);
        c.addHeader("X-MEET-APP-ID", AppConstants.PF_REQUEST_APP_ID);
        c.addHeader("X-MEET-API-TOKEN", AppUtils.getAPIToken(uid, token, url));
        return c;
    }

    public static AsyncHttpClient createClient(String url){
        return createClient(url, null, null);
    }
}
