package yinyueba.meetstudio.com.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import yinyueba.meetstudio.com.utils.UrlSafeBase64;

public class AppUtils {

	private final static String TAG = "AppUtils";

	public static String getAPIToken(String userId, String token, String url) {

		// 1.对url进行urlbase64加密
		String encodedUrlString = UrlSafeBase64.encodeToString(url);
		String jsonString = "{\"url\":\"";

		// 2.生成Json字符串
		jsonString += encodedUrlString + "\",\"appId\":\"" + AppConstants.PF_REQUEST_APP_ID + "\",\"apiVersion\":\""
				+ AppConstants.PF_REQUEST_API_VERSION + "\"";
		
		if (!TextUtils.isEmpty(userId)) {
			jsonString += ",\"userId\":\"" + userId + "\"";
		}

		if (!TextUtils.isEmpty(token)) {
			jsonString += ",\"userToken\":\"" + token + "\"";
		}
		jsonString += "}";
		
		try {
			// 检测JSON字符串正确性
			new JSONObject(jsonString);
			Log.i(TAG, "Json parse success");
		} catch (JSONException e1) {
			Log.e(TAG, "Json Error!");
			e1.printStackTrace();
		}
		Log.i(TAG, "jsonString = " + jsonString);
		
		// 3.对JSON字符串进行UrlSafeBase64编码
		String encodedJsonString = UrlSafeBase64.encodeToString(jsonString);
		
		try {
			// 4.对Json字符串进行HMAC_SHA1加密
			byte[] hmacsha1SignBytes = hmacsha1Sign(encodedJsonString, AppConstants.PF_REQUEST_SHA1_KEY);
			
			// 5.对HMAC_SHA1加密结果进行URL_BASE64加密
			String sign = UrlSafeBase64.encodeToString(hmacsha1SignBytes);
			Log.i(TAG, "sign = " + sign);

			return sign;
		} catch (InvalidKeyException e) {
			// 加密秘钥错误
			Log.e(TAG, "InvalidKeyException");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// 加密算法错误
			Log.e(TAG, "NoSuchAlgorithmException");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// 不支持的编码
			Log.e(TAG, "UnsupportedEncodingException");
			e.printStackTrace();
		}

		// 加密错误
		Log.e(TAG, "Sign Error!");
		return "";
	}

	private static byte[] hmacsha1Sign(String msg, String key) throws NoSuchAlgorithmException, InvalidKeyException,
			UnsupportedEncodingException {
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
		mac.init(secret);
		byte[] signBytes = mac.doFinal(msg.getBytes());
		return signBytes;
	}

	public static String getUserAgent(Context context) {
		String appName = "YCHMusic";
		String appVersion = getAppVersion(context);
		String device_name = android.os.Build.BRAND + " " + android.os.Build.MODEL;
		String os_name = "Android";
		String os_version = android.os.Build.VERSION.RELEASE;
		String locale = Locale.getDefault().getLanguage();
		
		String userAgent = String.format("%s %s (%s; %s %s; %s)", appName, appVersion, device_name, os_name, os_version, locale);
		return userAgent;
	}
	
	private static String getAppVersion(Context context) {
		// 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
        String version = ""; 
        if (packInfo != null)
        	version = packInfo.versionName;
        
        return version;
	}

	public static String getApiUrl(String path){
		return AppConstants.PLATFORM_ACCOUNT_BASE_URL + path;
	}
}
