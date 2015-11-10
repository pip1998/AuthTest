package yinyueba.meetstudio.com.library;

import android.content.Context;

import yinyueba.meetstudio.com.net.http.RequestParams;

/**
 * 作者：chaoyongzhang on 15/11/10 10:36
 * 邮箱：zhangcy@meet-future.com
 */
public class YCHUserAPI extends YCHOpenAPI {
    private String appKey;

    public YCHUserAPI(Context context, String appKey, String token) {
        super(context, appKey, token);
    }

    public void asyncUserInfo(String userId, YCHRequestHandler handler){
        String url = "http://www.baidu.com";
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        requestAsync(url, params, "GET", handler);
    }

    public void asyncVipInfo(String userId, YCHRequestHandler handler){

    }
}
