package yinyueba.meetstudio.com.library.model;

import org.json.JSONException;
import org.json.JSONObject;

import yinyueba.meetstudio.com.config.AppConstants;

/**
 * 作者：chaoyongzhang on 15/11/10 16:50
 * 邮箱：zhangcy@meet-future.com
 */
public class UserBean {
    String id;//	:	597

    String nickname;//	:	Kid1

    String gender;//	:	0

    String birthday;//	:	1989-12-12

    String portrait;//	:	7010

    String album;//	:	7010,6345,5649,6987,7005,7006

    String tags;//	:	古筝 钢琴 电吉他 贝斯 架子鼓

    String description;//	:	可及了把 v 回家使得分北方股份队

    String super_vip;//	:	1

    String vip_expire;//	:	2150162047

    String call_price;//	:	1

    String city;

    public static UserBean parse(String json){
        JSONObject object = null;
        try {
            object = new JSONObject(json).optJSONObject("user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UserBean bean = new UserBean();
        bean.id = object.optString("id");
        bean.nickname = object.optString("nickname");
        bean.gender = object.optString("gender");
        bean.birthday = object.optString("birthday");
        bean.portrait = object.optString("portrait");
        bean.album = String.format("%s%s?id=%s", AppConstants.PLATFORM_SERVER_ATTACHMENT_URL, AppConstants.PLATFORM_API_IMAGE, object.optString("album"));
        bean.tags = object.optString("tags");
        bean.description = object.optString("description");
        bean.super_vip = object.optString("super_vip");
        bean.vip_expire = object.optString("vip_expire");
        bean.call_price = object.optString("call_price");
        bean.city = object.optString("city");
        return bean;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getAlbum() {
        return album;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public String getSuper_vip() {
        return super_vip;
    }

    public String getVip_expire() {
        return vip_expire;
    }

    public String getCall_price() {
        return call_price;
    }

    public String getCity() {
        return city;
    }
}
