package yinyueba.meetstudio.com.library.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：chaoyongzhang on 15/11/10 17:17
 * 邮箱：zhangcy@meet-future.com
 */
public class PropertyBean {
    String id;//	:	597

    String nickname;//	:	Kid1

    String phone;//	:	0

    String super_vip;//	:	1

    String vip_expire;//	:	2150162047

    String call_price;//	:	1

    String point;

    String coin;

    String rmb;

    public static PropertyBean parse(String json){
        JSONObject object = null;
        try {
            object = new JSONObject(json).optJSONObject("userProperty");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PropertyBean bean = new PropertyBean();
        bean.id = object.optString("id");
        bean.nickname = object.optString("nickname");
        bean.phone = object.optString("phone");
        bean.super_vip = object.optString("super_vip");
        bean.vip_expire = object.optString("vip_expire");
        bean.call_price = object.optString("call_price");
        bean.coin = object.optString("coin");
        bean.point = object.optString("point");
        bean.rmb = object.optString("rmb");
        return bean;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
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

    public String getPoint() {
        return point;
    }

    public String getCoin() {
        return coin;
    }

    public String getRmb() {
        return rmb;
    }
}
