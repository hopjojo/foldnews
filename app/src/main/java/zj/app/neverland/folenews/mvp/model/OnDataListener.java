package zj.app.neverland.folenews.mvp.model;

/**
 * Created by cefoc on 2017/4/1.
 */

public interface OnDataListener<T> {
    void Success(T res);

    void Failed(String e);
}
