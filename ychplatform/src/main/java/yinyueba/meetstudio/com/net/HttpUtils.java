package yinyueba.meetstudio.com.net;

import yinyueba.meetstudio.com.net.http.AsyncHttpClient;
import yinyueba.meetstudio.com.net.http.AsyncHttpResponseHandler;
import yinyueba.meetstudio.com.net.http.BinaryHttpResponseHandler;
import yinyueba.meetstudio.com.net.http.JsonHttpResponseHandler;
import yinyueba.meetstudio.com.net.http.RequestParams;

/**
 * 作者：chaoyongzhang on 15/11/10 15:41
 * 邮箱：zhangcy@meet-future.com
 */
public class HttpUtils {
    private static AsyncHttpClient client =new AsyncHttpClient();    //实例话对象
    static
    {
        client.setTimeout(11000);   //设置链接超时，如果不设置，默认为10s
    }
    public static void get(String urlString,AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
        client.get(urlString, res);
    }
    public static void get(String urlString,RequestParams params,AsyncHttpResponseHandler res)   //url里面带参数
    {
        client.get(urlString, params,res);
    }
    public static void get(String urlString,JsonHttpResponseHandler res)   //不带参数，获取json对象或者数组
    {
        client.get(urlString, res);
    }
    public static void get(String urlString,RequestParams params,JsonHttpResponseHandler res)   //带参数，获取json对象或者数组
    {
        client.get(urlString, params,res);
    }
    public static void get(String uString, BinaryHttpResponseHandler bHandler)   //下载数据使用，会返回byte数据
    {
        client.get(uString, bHandler);
    }
    public static AsyncHttpClient getClient()
    {
        return client;
    }
}
