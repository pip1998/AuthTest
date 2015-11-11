package yinyueba.meetstudio.com.library;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import org.apache.http.Header;

import yinyueba.meetstudio.com.net.HttpUtils;
import yinyueba.meetstudio.com.net.http.RequestParams;
import yinyueba.meetstudio.com.net.http.TextHttpResponseHandler;

/**
 * 作者：chaoyongzhang on 15/11/10 10:56
 * 邮箱：zhangcy@meet-future.com
 */
public class YCHAuthAPI {
    public YCHAuthAPI(){

    }
    public void registerApp(Activity activity, String appKey, String state, String scope){
        String url = String.format("yinyuba://open/oauth?pid=%s&appId=%s&scope=%s&state=%s", activity.getPackageName(), appKey, scope, state);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.putExtra("pkg", activity.getPackageName());
        intent.putExtra("host", "yypapa.YYBInfoActivity");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void handleIntent(Intent intent, YCHRequestHandler handler){
        asyncBaseInfo(intent.getStringExtra("code"), handler);
    }

    private void asyncBaseInfo(String code, final YCHRequestHandler handler){
        RequestParams params = new RequestParams();
        params.put("code", code);
        String url = "http://www.baidu.com";
        HttpUtils.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (handler != null){
                    handler.onYCHRequestFailed(responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null){
                    handler.onYCHRequestSuccess(responseString);
                }
            }
        });
    }
}
