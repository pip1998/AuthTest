package yinyueba.meetstudio.com.library.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：chaoyongzhang on 15/11/10 17:17
 * 邮箱：zhangcy@meet-future.com
 */
public class AuthBean {
    private String token;
    private String userId;
    private String nickname;
    private String state;

    public static AuthBean parse(String json){
        AuthBean bean = new AuthBean();
        try {
            JSONObject object = new JSONObject(json);
            bean.token = object.optString("X-MEET-USER-TOKEN");
            bean.userId = object.optJSONObject("user").optString("id");
            bean.nickname = object.optJSONObject("user").optString("nickname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getState() {
        return state;
    }
}
