package yinyueba.meetstudio.com.library.model;

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
        UserBean bean = new UserBean();
        bean.id = "741";
        bean.nickname = "oooo";
        bean.gender = "1";
        bean.birthday = "1989-12-12";
        bean.portrait = "7010";
        bean.album = "7010,6345,5649,6987,7005,7006";
        bean.tags = "古筝 钢琴 电吉他 贝斯 架子鼓";
        bean.description = "平凡的世界";
        bean.super_vip = "1";
        bean.vip_expire = "2150162047";
        bean.call_price = "1";
        bean.city = "成都";
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