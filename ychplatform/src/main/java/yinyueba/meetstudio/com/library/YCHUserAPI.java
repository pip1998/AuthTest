package yinyueba.meetstudio.com.library;

import android.content.Context;

import yinyueba.meetstudio.com.config.AppConstants;
import yinyueba.meetstudio.com.config.AppUtils;
import yinyueba.meetstudio.com.net.http.RequestParams;

/**
 * 作者：chaoyongzhang on 15/11/10 10:36
 * 邮箱：zhangcy@meet-future.com
 */
public class YCHUserAPI extends YCHOpenAPI {
    private String appKey;

    public YCHUserAPI(Context context, String appKey, String token, String userId) {
        super(context, appKey, token, userId);
    }


    public void asyncUserInfo(String userId, YCHRequestHandler handler){
        String url = AppUtils.getApiUrl(AppConstants.PLATFORM_API_USER);
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        requestAsync(url, params, "GET", handler);
    }

    public void asyncVipInfo(String userId, YCHRequestHandler handler){
        String url = AppUtils.getApiUrl(AppConstants.PLATFORM_API_USER_PROPERTY);
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        requestAsync(url, params, "GET", handler);
    }
}
