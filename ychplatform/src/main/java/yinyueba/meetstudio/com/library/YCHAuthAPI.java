package yinyueba.meetstudio.com.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.apache.http.Header;

import yinyueba.meetstudio.com.config.AppUtils;
import yinyueba.meetstudio.com.library.model.ErrorBean;
import yinyueba.meetstudio.com.net.HttpUtils;
import yinyueba.meetstudio.com.net.http.AsyncHttpClient;
import yinyueba.meetstudio.com.net.http.RequestParams;
import yinyueba.meetstudio.com.net.http.TextHttpResponseHandler;

/**
 * 作者：chaoyongzhang on 15/11/10 10:56
 * 邮箱：zhangcy@meet-future.com
 */
public class YCHAuthAPI {
    private static final String TAG = "YCHAuthAPI";
    /**
     * oauth
     */
    static String LOGIN_AUTH									="/login/oauth";
    private final Context context;

    public YCHAuthAPI(Context context){
        this.context = context;
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
        Uri uri = intent.getData();
        String errorCode = getErrorCode(intent);
        if (!TextUtils.isEmpty(errorCode)){
            if (!errorCode.equals("0")){
                handler.onYCHRequestFailed(ErrorBean.createWithErrorCode(Integer.valueOf(errorCode).intValue()));
            }
            return;
        }
        asyncBaseInfo(uri.getQueryParameter("code"), handler);
    }

    public String getState(Intent intent){
        return intent.getData().getQueryParameter("state");
    }

    public String getErrorCode(Intent intent){
        return intent.getData().getQueryParameter("errorCode");
    }

    private void asyncBaseInfo(String code, final YCHRequestHandler handler){
        RequestParams params = new RequestParams();
        params.put("code", code);
        String url = AppUtils.getApiUrl(LOGIN_AUTH);
        AsyncHttpClient c = HttpUtils.createClient(AsyncHttpClient.getUrlWithQueryString(false, url, params), null, null);
        c.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (handler != null) {
                    handler.onYCHRequestFailed(ErrorBean.createWithStatusCode(statusCode, responseString));
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (handler != null) {
                    handler.onYCHRequestSuccess(responseString);
                }
            }
        });
    }

    public boolean isYCHAppInstalled(){
        try {
            return this.context.getPackageManager().getPackageInfo("com.meetstudio.yinyueba", 64) != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final int getYCHAppSupportAPI() {
        if(!this.isYCHAppInstalled()) {
            Log.i(TAG, "open ych app failed, not installed or signature check failed");
            return 0;
        } else {
            try {
                PackageInfo var = context.getPackageManager().getPackageInfo("com.meetstudio.yinyueba", 64);
                return var.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    public boolean isYCHAppSupportAPI(){
        return getYCHAppSupportAPI()>=18;
    }

    public boolean openYCHApp(){
        if (!isYCHAppInstalled()){
            Log.i(TAG, "the ych app has not been installed");
        }else {
            try {
                this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.meetstudio.yinyueba"));
                return true;
            } catch (Exception var2) {
                Log.i(TAG, "startActivity fail, exception = " + var2.getMessage());
                return false;
            }
        }
        return false;
    }
}
