package openauth.zhangcy.com.authtest.yypapa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import openauth.zhangcy.com.authtest.R;
import yinyueba.meetstudio.com.library.YCHAuthAPI;
import yinyueba.meetstudio.com.library.YCHRequestHandler;
import yinyueba.meetstudio.com.library.model.AuthBean;

public class YYBInfoActivity extends Activity {
    Intent intent;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yybinfo);
        intent = getIntent();
        Log.d("YYBInfoActivity", "pid = " + android.os.Process.myPid() + "errorCode = " + intent.getData().getQueryParameter("errorCode"));
        textView = (TextView) findViewById(R.id.text);
        YCHAuthAPI auth = new YCHAuthAPI();
        auth.handleIntent(intent, new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                AuthBean bean = AuthBean.parse(msg);
                textView.setText("auth result: " + bean.getToken());
            }

            @Override
            public void onYCHRequestFailed(String msg) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        textView.setText("auth result: " + intent.getData().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yybinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
