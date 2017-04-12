package zj.app.neverland.folenews.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


import java.util.List;

import zj.app.neverland.folenews.R;
import zj.app.neverland.folenews.base.SharedPreferencesUtils;
import zj.app.neverland.folenews.mvp.model.NewsInfo;


/**
 * Created by ZJ on 2017/2/10.
 */
@SuppressLint("SetJavaScriptEnabled")
public class ShowWebViewActivity extends AppCompatActivity {
    //    private WJBridgeWebView webView_content;
    private WebView webView_content;

    private Toolbar head_view;
    //    private CustomSwipeRefreshLayout swipeLayout;
    private ProgressBar bar;
    private Context context;
    private String cookieVal;
    private int moduleid;
    private String module_uri;
    private boolean first = false;
    private int id;
    private NewsInfo.NewInfo mInfo;
    private List<NewsInfo.NewInfo> mlist;
    private SharedPreferencesUtils prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.layout_show_webview);
        prefs = new SharedPreferencesUtils(context);
        mlist = prefs.getDataList("info", NewsInfo.NewInfo.class);
        mInfo = (NewsInfo.NewInfo) getIntent().getSerializableExtra("info");
        module_uri = mInfo.getDocurl();
        if (module_uri != null)
            initView();
        else
            Toast.makeText(this, "找不到网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newsinfo_toolbar_menu, menu);
        first = true;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mlist.contains(mInfo)) {
            menu.findItem(R.id.infoitem_save).setVisible(false);
            menu.findItem(R.id.infoitem_cancel).setVisible(true);
        } else {

            menu.findItem(R.id.infoitem_save).setVisible(true);
            menu.findItem(R.id.infoitem_cancel).setVisible(false);
        }
        if (first) {
            for (int i = 0; i < mlist.size(); i++) {
                try {
                    NewsInfo.NewInfo ss = mlist.get(i);
                    if (ss.getTitle().equals(mInfo.getTitle())) {
                        menu.findItem(R.id.infoitem_save).setVisible(false);
                        menu.findItem(R.id.infoitem_cancel).setVisible(true);
                        break;
                    }
                } catch (Exception ex) {
                }
            }
            first = false;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.infoitem_save:
                mlist.add(mInfo);
                prefs.setDataList("info", mlist);
                invalidateOptionsMenu();
                return true;
            case R.id.infoitem_cancel:
                if (!mlist.remove(mInfo)) {
                    for (int i = 0; i < mlist.size(); i++) {
                        if (mlist.get(i).getId() == mInfo.getId()) {
                            mlist.remove(i);
                            break;
                        }
                    }
                }
                prefs.setDataList("info", mlist);
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void getInstance(Context context, NewsInfo.NewInfo newInfo) {
        Intent i = new Intent();
        i.putExtra("info", newInfo);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(context, ShowWebViewActivity.class);
        context.startActivity(i);
    }

    private void initView() {
//        webView_content = (WJBridgeWebView) findViewById(R.id.web_content);
        webView_content = (WebView) findViewById(R.id.web_content);
//        swipeLayout = (CustomSwipeRefreshLayout) findViewById(R.id.swipe_container);
//        swipeLayout.setViewGroup(webView_content);
//        swipeLayout.setColorScheme(android.R.color.holo_blue_dark,
//                android.R.color.holo_purple,
//                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        webView_content.setBackgroundColor(0); // 设置背景色
        head_view = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(head_view);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        bar = (ProgressBar) findViewById(R.id.myProgressBar);
//        ToJsApi.registerApi(this, webView_content);

        WebSettings websetting = webView_content.getSettings();
//        websetting.setBlockNetworkImage(false);
        websetting.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        websetting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        websetting.setJavaScriptEnabled(true);
        websetting.setDomStorageEnabled(true);
        websetting.setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小
        String appCacheDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        websetting.setAppCachePath(appCacheDir);
        websetting.setAllowFileAccess(true);
        websetting.setAppCacheEnabled(true);
        websetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        websetting.setSupportZoom(true);
        websetting.setBuiltInZoomControls(true);
        //Cookie操作
//        cookieVal = GetCookieForNet.MOSOA + "=" + cookieVal;// + "; domain=" + cookie.getDomain()
//        boolean fl = BrowserUseFun.syncCookie(context, module_uri, cookieVal);
        webView_content.loadUrl(module_uri);//"file:///android_asset/test.html"
        webView_content.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                Log.i("打印日志", "网页加载失败");
            }
        });
        //进度条
        webView_content.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    Log.i("打印日志", "加载完成");
                }
            }
        });
        webView_content.setWebChromeClient(new WebChromeClient() {
            //扩充缓存的容量
            @Override
            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, com.tencent.smtt.sdk.WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(spaceNeeded * 2);
            }

            @Override
            public void onProgressChanged(com.tencent.smtt.sdk.WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
//                    swipeLayout.setRefreshing(false);
                } else {
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(com.tencent.smtt.sdk.WebView view, String title) {
                super.onReceivedTitle(view, title);
                head_view.setTitle(title);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView_content != null) webView_content.destroy();
    }
}
