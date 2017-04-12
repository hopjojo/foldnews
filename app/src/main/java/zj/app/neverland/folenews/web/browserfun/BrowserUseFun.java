package zj.app.neverland.folenews.web.browserfun;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;

/**
 * Created by zj on 2017/2/13.
 */

public class BrowserUseFun {
    /**
     * 将cookie同步到WebView
     *
     * @param context
     * @param url     WebView要加载的url
     * @param cookie  要同步的cookie
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */
    public static boolean syncCookie(Context context, String url, String cookie) {
//        cookieManager.setAcceptThirdPartyCookies(webview, true);
        CookieSyncManager.createInstance(context);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();

//        cookieManager.setCookie(url, cookie);
//        cookieManager.setCookie(url, "path=" + "");

        cookieManager.setCookie(url, cookie +";path=/");//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可//;domain=/
        CookieSyncManager.getInstance().sync();
        String newCookie = cookieManager.getCookie(url);
        return TextUtils.isEmpty(newCookie) ? false : true;
    }

    /**
     * 配置不锁屏
     *
     * @param context
     */
    public static void noNeedLockApp(Context context) {
        Intent intent = new Intent(LockAppReceiver.ACTION_LOCK_APP);
        intent.putExtra(LockAppReceiver.ACTION_LOCK_APP, LockAppReceiver.UN_NEEDLOCK);
        context.sendBroadcast(intent);
    }

    //将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }
}
