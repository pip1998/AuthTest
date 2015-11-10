package yinyueba.meetstudio.com.library.model;

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
        bean.token = "test";
        bean.userId = "741";
        bean.nickname = "oooo";
        bean.state = "00";
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
