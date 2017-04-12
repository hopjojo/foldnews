package zj.app.neverland.folenews.mvp.model;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by cefoc on 2017/4/1.
 */

public class NewsDataCallBack extends Callback<NewsInfo> {

    @Override
    public NewsInfo parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        NewsInfo user = new Gson().fromJson(string, NewsInfo.class);
        return user;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(NewsInfo response, int id) {

    }
}
