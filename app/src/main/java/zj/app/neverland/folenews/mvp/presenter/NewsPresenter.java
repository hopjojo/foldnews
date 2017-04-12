package zj.app.neverland.folenews.mvp.presenter;

import zj.app.neverland.folenews.mvp.model.OnDataListener;
import zj.app.neverland.folenews.mvp.view.INewsView;

/**
 * Created by cefoc on 2017/4/1.
 */

public interface NewsPresenter {
    String getNewsInfo(INewsView newsView, OnDataListener onDataListener);
}
