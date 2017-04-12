package zj.app.neverland.folenews.mvp.presenter;

import zj.app.neverland.folenews.mvp.model.NewsData;
import zj.app.neverland.folenews.mvp.model.NewsDataImp;
import zj.app.neverland.folenews.mvp.model.NewsInfo;
import zj.app.neverland.folenews.mvp.model.OnDataListener;
import zj.app.neverland.folenews.mvp.view.INewsView;

/**
 * Created by cefoc on 2017/4/1.
 */

public class NewsPresenterImp implements NewsPresenter {
    @Override
    public String getNewsInfo(final INewsView newsView,final OnDataListener dataListener) {
        String page = newsView.getPage();
        String type = newsView.getType();
        String limit = newsView.getLimit();
        NewsData newsData = new NewsDataImp();
        newsData.getNews(type, page, limit, new OnDataListener<NewsInfo>() {
            @Override
            public void Success(NewsInfo res) {
//                newsView.bindAdapter(res);
                dataListener.Success(res);
            }

            @Override
            public void Failed(String e) {
                dataListener.Failed(e);
            }
        });
        return null;
    }
}
