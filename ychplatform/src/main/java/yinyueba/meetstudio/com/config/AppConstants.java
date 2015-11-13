package yinyueba.meetstudio.com.config;


public interface AppConstants {

	//正式测试相应控制，1测试环境，0正式环境
	static Boolean IS_DEBUG_MODE              =false;

	//测试环境配置

	//接口授权
	static String PF_REQUEST_API_VERSION      ="1.0";
	static String PF_REQUEST_APP_ID           ="2";
	static String PF_REQUEST_SHA1_KEY         =IS_DEBUG_MODE?"123456":"1417691562-T78U6JFQSJHPZ1IATNCQ";

	//平台帐户服务
	//pragma mark - 测试环境配置 - 平台帐户服务
	static String PLATFORM_ACCOUNT_BASE_URL           =IS_DEBUG_MODE?"http://test.api.ychmusic.com":"http://api.yypapa.com";

	//开放平台服务
	static String PLATFORM_OPEN_BASE_URL				="http://" + (IS_DEBUG_MODE?"test.open.yypapa.com":"open.yypapa.com");

	//平台应用服务
	//pragma mark - 测试环境配置 - 平台应用服务
	static String PLATFORM_SERVER_BASE_URL            =IS_DEBUG_MODE?"http://test.api.ychmusic.com":"http://api.yypapa.com";
	static String PLATFORM_SERVER_ATTACHMENT_URL      =IS_DEBUG_MODE?"http://test.api.ychmusic.com":"http://api.yypapa.com";
    static String PLATFORM_MUSIC_WEB_SERVER           =IS_DEBUG_MODE?"http://test.www.ychmusic.com":"http://www.yypapa.com";



	//pragma mark - 平台接口 - 图片附件接口
	static String PLATFORM_API_IMAGE                  ="/img";//图片附件下载

	//pragma mark - 平台接口 - 用户接口
	static String PLATFORM_API_USER                   ="/user";//用户个人信息：仅限个人
	static String PLATFORM_API_USER_PROPERTY          ="/user/property";//用户属性
}
