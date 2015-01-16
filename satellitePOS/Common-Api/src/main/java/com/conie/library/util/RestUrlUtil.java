package com.conie.library.util;

public class RestUrlUtil {
	public static final String	WEB_SERVICE_DOMAIN	= System.getProperty("com.satellite.webservice.domain");
	public static final String	ACCOUNT_FIND		= WEB_SERVICE_DOMAIN + "account/find";
	public static final String	ACCOUNT_DELETE		= WEB_SERVICE_DOMAIN + "account/delete";
	public static final String	ACCOUNT_CREATE		= WEB_SERVICE_DOMAIN + "account/create";
	public static final String	ACCOUNT_UPDATE		= WEB_SERVICE_DOMAIN + "account/update";
	public static final String	ACCOUNT_FIND_ALL	= WEB_SERVICE_DOMAIN + "account/findall";

	public static final String	USER_TYPE_CREATE	= WEB_SERVICE_DOMAIN + "usertype/create";
	public static final String	USER_TYPE_UPDATE	= WEB_SERVICE_DOMAIN + "usertype/update";
	public static final String	USER_TYPE_FIND		= WEB_SERVICE_DOMAIN + "usertype/find";
	public static final String	USER_TYPE_FIND_ALL	= WEB_SERVICE_DOMAIN + "usertype/findall";
	public static final String	USER_TYPE_DELETE	= WEB_SERVICE_DOMAIN + "usertype/delete";

	public static final String	USER_CREATE			= WEB_SERVICE_DOMAIN + "user/create";
	public static final String	USER_UPDATE			= WEB_SERVICE_DOMAIN + "user/update";
	public static final String	USER_FIND			= WEB_SERVICE_DOMAIN + "user/find";
	public static final String	USER_FIND_ALL		= WEB_SERVICE_DOMAIN + "user/findall";
	public static final String	USER_DELETE			= WEB_SERVICE_DOMAIN + "user/delete";
	public static final String	USER_LOGIN			= WEB_SERVICE_DOMAIN + "user/login";
	public static final String	USER_LOGOUT_ALL		= WEB_SERVICE_DOMAIN + "user/logoutall";
	public static final String	USER_LOGOUT			= WEB_SERVICE_DOMAIN + "user/logout";
}
