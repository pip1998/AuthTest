package yinyueba.meetstudio.com.library;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.apache.http.Header;

import yinyueba.meetstudio.com.net.HttpUtils;
import yinyueba.meetstudio.com.net.http.AsyncHttpClient;
import yinyueba.meetstudio.com.net.http.RequestParams;
import yinyueba.meetstudio.com.net.http.TextHttpResponseHandler;

/**
 * 作者：chaoyongzhang on 15/11/10 15:27
 * 邮箱：zhangcy@meet-future.com
 */
public abstract class YCHOpenAPI {
    private static final String TAG = "YCHOpenAPI";
    protected Context mContext = null;
    protected String appKey = null;
    protected String mAccessToken = null;
    protected AsyncHttpClient client;
    public YCHOpenAPI(Context context, String appKey, String token){
        this.mContext = context;
        this.appKey = appKey;
        this.mAccessToken = token;
        this.client = HttpUtils.getClient();
        client.addHeader("access_token", token);
    }

    protected void requestAsync(String url, RequestParams params, String httpMethod, YCHRequestHandler listener) {
        TextHttpResponseHandler resListener = new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }
        };
        if(this.mAccessToken != null && !TextUtils.isEmpty(url) && params != null && !TextUtils.isEmpty(httpMethod) && listener != null) {
            params.put("access_token", this.mAccessToken);
            if (httpMethod.equalsIgnoreCase("GET")){
                client.get(mContext, url, params, resListener);
            }else {
                client.post(mContext, url, params, resListener);
            }
        } else {
            Log.e(TAG, "Argument error!");
        }
    }
}
