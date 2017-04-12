package zj.app.neverland.folenews.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SharedPreferencesUtils {

	public static final String				FIRST_RUNNING		= "FIRST_RUNNING";
	public static final String				FIRST_RUNNING_TASK		= "FIRST_RUNNING_TASK";//第一次运行工作任务/请示/求助
	public static final String				FIRST_RUNNING_LOGIN		= "FIRST_RUNNING_LOGIN";//第一次运行登录
	public static final String				FIRST_RUNNING_SELECTORG		= "FIRST_RUNNING_SELECTORG";//第一次运行选择组织
	public static final String				FIRST_RUNNING_SELECTORG_EMPTY		= "FIRST_RUNNING_SELECTORG_EMPTY";//第一次运行选择组织时没有组织
	public static final String				FIRST_RUNNING_ORGANIZATION		= "FIRST_RUNNING_ORGANIZATION";//第一次运行组织架构
	public static final String				FIRST_RUNNING_ORGANIZATION2		= "FIRST_RUNNING_ORGANIZATION2";//第一次运行组织架构
	public static final String				FIRST_RUNNING_ORGANIZATION4	= "FIRST_RUNNING_ORGANIZATION4";//第一次完善个人信息
	public static final String				FIRST_RUNNING_ORGANIZATION5	= "FIRST_RUNNING_ORGANIZATION5";//第一次完善个人信息
	public static final String				FIRST_RUNNING_ORGANIZATION6	= "FIRST_RUNNING_ORGANIZATION6";//第一次完善个人信息
	public static final String				FIRST_RUNNING_EDITORGANIZATION		= "FIRST_RUNNING_EDITORGANIZATION";//第一次运行编辑组织架构
	public static final String				FIRST_RUNNING_EDITMEMBER		= "FIRST_RUNNING_EDITMEMBER";//第一次运行编辑成员
	public static final String				FIRST_RUNNING_CREATEORGANIZATION		= "FIRST_RUNNING_CREATEORGANIZATION";//第一次运行创建组织架构
	public static final String				FIRST_RUNNING_CREATEMEMBER		= "FIRST_RUNNING_CREATEMEMBER";//第一次运行创建人员
	public static final String				FIRST_RUNNING_CHANGEHEAD		= "FIRST_RUNNING_CHANGEHEAD";//第一次修改头像
	public static final String				FIRST_RUNNING_SEEPERSONINFO		= "FIRST_RUNNING_SEEPERSONINFO";//第一次运行查看个人资料
	public static final String				FIRST_RUNNING_PERSONMOREINFO		= "FIRST_RUNNING_PERSONMOREINFO";//第一次个人中心右上角三个图标
	public static final String				FIRST_RUNNING_OtherpersonHead		= "FIRST_RUNNING_OtherpersonHead";//第一次他人个人中心头像
	public static final String				FIRST_RUNNING_otherpersontask	= "FIRST_RUNNING_otherpersontask";//第一次次他人个人中心任务
	public static final String				FIRST_RUNNING_persongroup	= "FIRST_RUNNING_persongroup";//第一次个人分组
	public static final String				FIRST_RUNNING_personcompanyname	= "FIRST_RUNNING_personcompanyname";//第一次个人分组
	public static final String				FIRST_RUNNING_persongroupadd	= "FIRST_RUNNING_persongroupadd";//第一次个人分组
	public static final String				FIRST_RUNNING_person_myticket	= "FIRST_RUNNING_person_myticket";//第一次个人分组
	public static final String				FIRST_RUNNING_person_task	= "FIRST_RUNNING_person_task";//第一次个人分组
	public static final String				FIRST_RUNNING_person_request	= "FIRST_RUNNING_person_request";//第一次个人分组
	public static final String				FIRST_RUNNING_person_ask	= "FIRST_RUNNING_person_ask";//第一次个人分组
	public static final String				FIRST_RUNNING_person_daily	= "FIRST_RUNNING_person_daily";//第一次个人分组
	public static final String				FIRST_RUNNING_person_living	= "FIRST_RUNNING_person_living";//第一次个人分组
	public static final String				FIRST_RUNNING_task	= "FIRST_RUNNING_task";//第一次下达任务
	public static final String				FIRST_RUNNING_task_addpeople	= "FIRST_RUNNING_task_addpeople";//第一次下达任务
	public static final String				FIRST_RUNNING_task_reply	= "FIRST_RUNNING_task_reply";//第一次下达任务
	public static final String				FIRST_RUNNING_task_repy	= "FIRST_RUNNING_task_repy";//第一次下达任务
	public static final String				FIRST_RUNNING_task_new	= "FIRST_RUNNING_task_new";//第一次下达任务
	public static final String				FIRST_RUNNING_task_info	= "FIRST_RUNNING_task_info";//第一次完善个人信息
	public static final String				FIRST_RUNNING_phone	= "FIRST_RUNNING_phone";//第一次电话
	public static final String				FIRST_RUNNING_personinfocompete	= "FIRST_RUNNING_personinfocompete";//第一次完善个人信息
	public static final String				FIRST_RUNNING_role_level	= "FIRST_RUNNING_role_level";//第一次完善个人信息
	public static final String				FIRST_RUNNING_organization_createsuccess	= "FIRST_RUNNING_organization_createsuccess";//第一次完善个人信息
	public static final String				FIRST_RUNNING_person_top	= "FIRST_RUNNING_person_top";//第一次完善个人信息
	public static final String				FIRST_RUNNING_person_bottom	= "FIRST_RUNNING_person_bottom";//第一次完善个人信息
	public static final String				FIRST_RUNNING_Myspace	= "FIRST_RUNNING_Myspace";//第一次个人空间
	public static final String				FIRST_RUNNING_colleague	= "FIRST_RUNNING_colleague";//第一次同事圈

	public static final String				FIRST_START_APP_PIC	= "FIRST_START_APP_PIC";//第一次启动app显示引导图
	
	public static final String				LOCK_PATTERN_KEY	= "LOCK_PATTERN_KEY";
	public static final String				LOCK_PATTERN_OPEN	= "LOCK_PATTERN_OPEN";
	public static final String				LOCK_PATTERN_RESET	= "LOCK_PATTERN_RESET";

	public static final String             SYS_SOUND = "sys_sound";
	
	public static final String DOWNLOAD_APK_DIALOG_HOW = "DOWNLOAD_APK_DIALOG_ISHOW";//是否显示版本下载弹框 1 提示框，2下载框
	public static final String DOWNLOAD_APK_DIALOG_TIP_HOW = "DOWNLOAD_APK_DIALOG_TIP_HOW";
	public static final String DOWNLOAD_APK_DIALOG_FORCELUPDAE = "DOWNLOAD_APK_DIALOG_FORCELUPDAE";
	
	public static final String MODIFY_PWD_DIALOG_TIP = "MODIFY_PWD_DIALOG_TIP";
	
	public static final String WHOSE_SETTING_DEFAULTPAGE = "whose_setting_default_page";
	public static final String DEFAULTPAGE = "default_page";
	
	private static final String				FILE_NAME			= "share_data";
	private static final String             UNSENDMSG           = "unsendmsg";

	////position_percent_state_statetip
	private static final String DOWNLOAD_INFO = "DOWNLOAD_INFO";
	
	private final String COMPANY_INFO = "company_info";
	private final String COMPNAY_BANNERIMG = "COMPANY_BANNERIMG";
	private final String COMPNAY_NAME = "company_name";
	private final String COMPNAY_SLOGANIMAGE = "company_sloganimage";
	private final String COMPNAY_SMALL_NAME = "company_small_name";
	private final String COMPANY_UPDATETIME = "company_updatetime";
	private final String COMPANY_EXPIRETEXT = "company_expiretime";
	private final String COMPANY_website = "company_website";
	
	private static SharedPreferencesUtils	instance			= null;

	private SharedPreferences				sharedPrefs			= null;
	private SharedPreferences.Editor editor;

	private final String SEA_NEW_FRIEND_UPDATETIME = "SEA_NEW_FRIEND_UPDATETIME";
	
	public static final String				FIRST_RUNNING_SEA_MAIN_ORG	= "FIRST_RUNNING_SEA_MAIN_ORG";
	public static final String				FIRST_SHOW_TRY_ORG_TIP	= "FIRST_SHOW_TRY_ORG_TIP";
	
	public SharedPreferencesUtils(Context context) {
		sharedPrefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		editor = sharedPrefs.edit();
	}

	public static SharedPreferencesUtils getInstance(Context context) {
		if (instance == null) {
			instance = new SharedPreferencesUtils(context);
		}

		return instance;
	}
	
//	private SharedPreferencesUtils(Context context,String unsendmsg) {
//		sharedPrefs = context.getSharedPreferences(unsendmsg, Context.MODE_PRIVATE);
//	}

	public SharedPreferencesUtils(Context context,String unsendmsg) {
		sharedPrefs = context.getSharedPreferences(unsendmsg, Context.MODE_PRIVATE);
	}
	
	public static SharedPreferencesUtils getInstanceUnSendMsg(Context context) {
		if (instance == null) {
			instance = new SharedPreferencesUtils(context,UNSENDMSG);
		}

		return instance;
	}



	/**
	 * 保存List
	 * @param tag
	 * @param datalist
	 */
	public <T> void setDataList(String tag, List<T> datalist) {
		if (null == datalist || datalist.size() <= 0)
			return;

		Gson gson = new Gson();
		//转换成json数据，再保存
		String strJson = gson.toJson(datalist);
		editor.clear();
		editor.putString(tag, strJson);
		editor.commit();

	}

	/**
	 * 获取List
	 * @param tag
	 * @return
	 */
	public <T> List<T> getDataList(String tag, Class<T> cls) {
		List<T> datalist=new ArrayList<T>();
		String strJson = sharedPrefs.getString(tag, null);
		if (null == strJson) {
			return datalist;
		}
		Gson gson = new Gson();
		JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
		for(final JsonElement elem : array){
			datalist.add(gson.fromJson(elem, cls));
		}
//		datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
//		}.getType());
		return datalist;
	}



	public void removeParam(String key) {
		if (sharedPrefs.contains(key)) {
			Editor editor = sharedPrefs.edit();
			editor.remove(key);
			editor.commit();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> void saveParam(String key, T value) {
		Class<?> cls = value.getClass();
		Editor editor = sharedPrefs.edit();

		if (cls == String.class) {
			editor.putString(key, (String) value);
		} else if (cls == Integer.class || cls == int.class) {
			editor.putInt(key, (Integer) value);
		} else if (cls == Boolean.class || cls == boolean.class) {
			editor.putBoolean(key, (Boolean) value);
		} else if (cls == Float.class || cls == float.class) {
			editor.putFloat(key, (Float) value);
		} else if (cls == Long.class || cls == long.class) {
			editor.putLong(key, (Long) value);
		} else if (value instanceof Editable) {
			editor.putString(key, value.toString());
		} else if (value instanceof Set) {
			editor.putStringSet(key, (Set<String>) value);
		}

		editor.commit();
	}

	@SuppressWarnings("unchecked")
	public <T> T getParam(String key, T defValue) {
		Class<?> cls = defValue.getClass();
		T val = null;
		if (cls == String.class) {
			val = (T) sharedPrefs.getString(key, (String) defValue);
		} else if (cls == Integer.class || cls == int.class) {
			val = (T) (Integer) sharedPrefs.getInt(key, (Integer) defValue);
		} else if (cls == Boolean.class || cls == boolean.class) {
			val = (T) (Boolean) sharedPrefs.getBoolean(key, (Boolean) defValue);
		} else if (cls == Float.class || cls == float.class) {
			val = (T) (Float) sharedPrefs.getFloat(key, (Float) defValue);
		} else if (cls == Long.class || cls == long.class) {
			val = (T) (Long) sharedPrefs.getLong(key, (Long) defValue);
		} else if (defValue instanceof Set) {
			val = (T) sharedPrefs.getStringSet(key, (Set<String>) defValue);
		} 

		return val;
	}

	public boolean contains(String key) {
		return sharedPrefs.contains(key);
	}
	
	public String getString(String key){
		return sharedPrefs.getString(key, null);
	}



	public void saveDownloadInfo(HashMap<Integer,String> downloadInfo){
		String key = DOWNLOAD_INFO ;
		SerializableUtils.save(key, downloadInfo);
	}
	
	public HashMap<Integer,String> getDownloadInfo(int position){
		String key = DOWNLOAD_INFO;
		return SerializableUtils.load(key);
	}
	
	public void clearDownloadInfo(){
		String key = DOWNLOAD_INFO;
		SerializableUtils.clear(key);
	}
	
	public int getSeaNewFriendSysMsgid(){
		return this.getParam(SEA_NEW_FRIEND_UPDATETIME,0);
	}
	
	public void saveSeaNewFriendSysMsgid(int msgid){
		this.saveParam(SEA_NEW_FRIEND_UPDATETIME, msgid);
	}
	
	public void saveLastLat(double lat){
		String value = String.valueOf(lat);
		this.saveParam("lastlat", value);
	}
	public double getLastLat(){
		String value = this.getParam("lastlat", "39.915599");
		return Double.parseDouble(value);
	}
	public void saveLastLng(double lng){
		String value = String.valueOf(lng);
		this.saveParam("lastlng", value);
	}
	public double getLastLng(){
		String value = this.getParam("lastlng", "116.403694");
		return Double.parseDouble(value);
	}
	
}
