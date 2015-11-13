package yinyueba.meetstudio.com.library.model;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：chaoyongzhang on 15/11/12 18:23
 * 邮箱：zhangcy@meet-future.com
 */
public class ErrorBean {
    enum DefaultError{
        UNKNOWN(-1, "未知错误"), ParamError(10001, "参数错误"), UserCancel(10002, "用户取消");
        ErrorBean bean;
        DefaultError(int errorCode, String msg){
            bean = new ErrorBean(errorCode, msg);
        }

        public ErrorBean getBean() {
            return bean;
        }

        public static DefaultError ValueOf(int errorCode){
            switch (errorCode){
                case 10001:
                    return ParamError;
                case 10002:
                    return UserCancel;
                default:
                    return UNKNOWN;
            }
        }
    }
    int errorCode;
    String description;
    public static ErrorBean parse(String json){
        JSONObject object = null;
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ErrorBean bean = new ErrorBean(object.optInt("errorCode"), object.optString("description"));
        return bean;
    }

    public static ErrorBean createWithStatusCode(int status, String msg){
        ErrorBean bean = new ErrorBean(11001, String.format("StatusCode: %d, response: %s", status, msg));
        return bean;
    }


    public static ErrorBean createWithErrorCode(int errorCode){
        return createWithErrorCode(errorCode, null);
    }

    public static ErrorBean createWithErrorCode(int errorCode, String msg){
        ErrorBean bean = new ErrorBean(errorCode, msg);
        if (TextUtils.isEmpty(msg)){
            return DefaultError.ValueOf(errorCode).getBean();
        }
        return bean;
    }

    public ErrorBean(int e, String description){
        this.errorCode = e;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("errorCode: %d, description: %s", errorCode, description);
    }
}
