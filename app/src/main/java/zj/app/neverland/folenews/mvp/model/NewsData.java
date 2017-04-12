package zj.app.neverland.folenews.mvp.model;

/**
 * Created by cefoc on 2017/4/1.
 */

public interface NewsData {
    /**
     * 请求新闻数据
     * @param type 新闻类型
     * @param page 当前页数
     * @param limit 请求条数
     */
    void getNews(String type,String page,String limit,final OnDataListener dataListener);
}
