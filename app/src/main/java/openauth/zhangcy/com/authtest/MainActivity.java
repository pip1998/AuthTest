package openauth.zhangcy.com.authtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yinyueba.meetstudio.com.library.YCHAuthAPI;
import yinyueba.meetstudio.com.library.YCHRequestHandler;
import yinyueba.meetstudio.com.library.YCHUserAPI;
import yinyueba.meetstudio.com.library.model.ErrorBean;
import yinyueba.meetstudio.com.library.model.PropertyBean;
import yinyueba.meetstudio.com.library.model.UserBean;

public class MainActivity extends Activity {
    TextView textView = null;
    TextView connect = null;

    Button authBtn = null;
    Button userBtn = null;
    Button propertyBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "pid = " + android.os.Process.myPid());
        textView = (TextView) findViewById(R.id.result);
        connect = (TextView) findViewById(R.id.connect);
        authBtn = (Button) findViewById(R.id.authBtn);
        userBtn = (Button) findViewById(R.id.userBtn);
        propertyBtn = (Button) findViewById(R.id.propertyBtn);
    }


    @Override
    protected void onResume() {
        super.onResume();
        connect.setText(Contacts.authBean == null?"未连接":"已连接");
        boolean apiEnable = Contacts.authBean != null;
        userBtn.setEnabled(apiEnable);
        propertyBtn.setEnabled(apiEnable);
    }

    public void onOpen(View view){
        YCHAuthAPI api = new YCHAuthAPI(this);
        api.openYCHApp();
    }

    public void onAuth(View view){
        //auth
        YCHAuthAPI api = new YCHAuthAPI(this);
        if (api.isYCHAppSupportAPI()&&api.isYCHAppInstalled()){
            api.registerApp(this, "12345", "00", "login");

        }
    }

    public void onUser(View view){
        YCHUserAPI api = new YCHUserAPI(this, "12345", Contacts.authBean.getToken(), Contacts.authBean.getUserId());
        api.asyncUserInfo(Contacts.authBean.getUserId(), new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                UserBean userBean = UserBean.parse(msg);
                textView.setText(userBean.getNickname());
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
                textView.setText(bean.getDescription());
            }
        });
    }

    public void onProperty(View view){
        YCHUserAPI api = new YCHUserAPI(this, "12345", Contacts.authBean.getToken(), Contacts.authBean.getUserId());
        api.asyncVipInfo(Contacts.authBean.getUserId(), new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                PropertyBean bean = PropertyBean.parse(msg);
                textView.setText(bean.getCall_price());
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
                textView.setText(bean.getDescription());
            }
        });
    }
}
