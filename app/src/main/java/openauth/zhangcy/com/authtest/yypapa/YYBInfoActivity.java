package openauth.zhangcy.com.authtest.yypapa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import openauth.zhangcy.com.authtest.Contacts;
import openauth.zhangcy.com.authtest.R;
import yinyueba.meetstudio.com.library.YCHAuthAPI;
import yinyueba.meetstudio.com.library.YCHRequestHandler;
import yinyueba.meetstudio.com.library.model.AuthBean;
import yinyueba.meetstudio.com.library.model.ErrorBean;

public class YYBInfoActivity extends Activity {
    private static final String TAG = "YYBInfoActivity";
    Intent intent;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yybinfo);
        intent = getIntent();
        Log.d("YYBInfoActivity", "pid = " + android.os.Process.myPid() + "errorCode = " + intent.getData().getQueryParameter("errorCode"));
        textView = (TextView) findViewById(R.id.text);
        YCHAuthAPI auth = new YCHAuthAPI(this);
        auth.handleIntent(intent, new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                AuthBean bean = AuthBean.parse(msg);
                textView.setText("auth result: " + bean.getToken());
                Contacts.authBean = bean;
                finish();
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
                Log.i(TAG, bean.toString());
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        textView.setText("auth result: " + intent.getData().toString());
    }
}
