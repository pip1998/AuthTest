package yinyueba.meetstudio.com.library;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import yinyueba.meetstudio.com.config.AppUtils;
import yinyueba.meetstudio.com.library.model.ErrorBean;
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
    protected String mUserId = null;
    public YCHOpenAPI(Context context, String appKey, String token, String userId){
        this.mContext = context;
        this.appKey = appKey;
        this.mAccessToken = token;
        this.mUserId = userId;
    }

    protected boolean requestAsync(String url, RequestParams params, String httpMethod, final YCHRequestHandler listener) {
        TextHttpResponseHandler resListener = new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onYCHRequestFailed(ErrorBean.createWithStatusCode(statusCode, responseString));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject object = new JSONObject(responseString);
                    int errorCode = object.optInt("errorCode");
                    if (errorCode != 0){
                        listener.onYCHRequestFailed(ErrorBean.parse(responseString));
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onYCHRequestSuccess(responseString);
            }
        };
        if(!TextUtils.isEmpty(this.mAccessToken) && !TextUtils.isEmpty(url) && !TextUtils.isEmpty(mUserId) && !TextUtils.isEmpty(httpMethod) && listener != null) {
            AsyncHttpClient client = HttpUtils.createClient(AsyncHttpClient.getUrlWithQueryString(true, url, params), mUserId, mAccessToken);
            client.setUserAgent(AppUtils.getUserAgent(mContext));
            if (httpMethod.equalsIgnoreCase("GET")){
                client.get(url, params, resListener);
            }else {
                client.post(url, params, resListener);
            }
        } else {
            Log.e(TAG, "Argument error!");
            return false;
        }
        return true;
    }
}
