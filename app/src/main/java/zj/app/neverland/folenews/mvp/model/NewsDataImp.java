package zj.app.neverland.folenews.mvp.model;

import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by cefoc on 2017/4/1.
 */

public class NewsDataImp implements NewsData {
    @Override
    public void getNews(String type, String page, String limit, final OnDataListener dataListener) {
        String url = "http://wangyi.butterfly.mopaasapp.com/news/api";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("type", type)
                .addParams("page", page)
                .addParams("limit", limit)
                .build()
                .execute(new NewsDataCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dataListener.Failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(NewsInfo response, int id) {
                        if (response.getList().size() == 0)
                            dataListener.Failed("没有数据");
                        else
                            dataListener.Success(response);
                    }
                });
    }
}
