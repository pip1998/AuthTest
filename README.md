# AuthTest

### 打开音约吧

```java
YCHAuthAPI api = new YCHAuthAPI(activity);
api.openYCHApp();
```
### 授权

##### 开始授权

```java
YCHAuthAPI api = new YCHAuthAPI(activity);
if (api.isYCHAppSupportAPI()&&api.isYCHAppInstalled()){
      api.registerApp(activity, appkey, state, "login");
}
```
##### 授权回调
* 新建一个名为.yypapa.YYBInfoActivity的Activity
```xml
        <activity
            android:name=".yypapa.YYBInfoActivity"
            android:label="@string/title_activity_yybinfo"
            android:launchMode="singleTop"
            android:exported="true">
        </activity>
```
* 授权的结果会通过Intent传给该Activity
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yybinfo);
        intent = getIntent();
        textView = (TextView) findViewById(R.id.text);
        YCHAuthAPI auth = new YCHAuthAPI(this);
        auth.handleIntent(intent, new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                AuthBean bean = AuthBean.parse(msg);
                textView.setText("auth result: " + bean.getToken());
                Contacts.authBean = bean;//保存授权信息
                finish();
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
                Log.i(TAG, bean.toString());
                finish();
            }
        });
    }
```
### 异步获取用户信息
```java
YCHUserAPI api = new YCHUserAPI(activity, appkey, Contacts.authBean.getToken(), Contacts.authBean.getUserId());
        api.asyncUserInfo(Contacts.authBean.getUserId(), new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                UserBean userBean = UserBean.parse(msg);
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
            }
        });
```
### 异步获取用户属性
```java
YCHUserAPI api = new YCHUserAPI(activity, appkey, Contacts.authBean.getToken(), Contacts.authBean.getUserId());
        api.asyncVipInfo(Contacts.authBean.getUserId(), new YCHRequestHandler() {
            @Override
            public void onYCHRequestSuccess(String msg) {
                PropertyBean bean = PropertyBean.parse(msg);
            }

            @Override
            public void onYCHRequestFailed(ErrorBean bean) {
                textView.setText(bean.getDescription());
            }
        });
```
