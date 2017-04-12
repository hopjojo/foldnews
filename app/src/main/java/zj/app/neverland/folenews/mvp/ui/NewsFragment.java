package zj.app.neverland.folenews.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zj.app.neverland.folenews.R;
import zj.app.neverland.folenews.adapter.NewsAdapter;
import zj.app.neverland.folenews.app.NewItem;
import zj.app.neverland.folenews.base.BaseAdapter;
import zj.app.neverland.folenews.base.ILoadCallback;
import zj.app.neverland.folenews.base.LoadMoreAdapterWrapper;
import zj.app.neverland.folenews.base.OnLoad;
import zj.app.neverland.folenews.mvp.model.NewsInfo;
import zj.app.neverland.folenews.mvp.model.OnDataListener;
import zj.app.neverland.folenews.mvp.presenter.NewsPresenter;
import zj.app.neverland.folenews.mvp.presenter.NewsPresenterImp;
import zj.app.neverland.folenews.mvp.view.INewsView;
import zj.app.neverland.folenews.web.ShowWebViewActivity;

/**
 * Created by zj on 2017/4/1.
 */

public class NewsFragment extends Fragment implements INewsView {

    @BindView(R.id.rv_newslist)
    RecyclerView rvNewslist;
    Unbinder unbinder;
    BaseAdapter adapter;
    private INewsView newsView;
    private NewsPresenter newsPresenter = new NewsPresenterImp();
    private List<NewsInfo.NewInfo> minfo = new ArrayList<>();
    private NewsInfo info;
    private int page = 1;
    private NewItem type;

    public static NewsFragment newInstance(NewItem item) {
        NewsFragment newFragment = new NewsFragment();
        Bundle b = new Bundle();
        b.putSerializable("Type", item);
        newFragment.setArguments(b);
        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = (NewItem) args.getSerializable("Type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);

        newsView = this;
        final NewsAdapter mAdapter = new NewsAdapter(getContext().getApplicationContext());
        mAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data) {
                NewsInfo.NewInfo info = (NewsInfo.NewInfo)data;
                ShowWebViewActivity.getInstance(getContext().getApplicationContext(),info);
            }
        });
        adapter = new LoadMoreAdapterWrapper(mAdapter, new OnLoad() {
            @Override
            public void load(final int pagePosition, int pageSize, final ILoadCallback callback) {
                newsPresenter.getNewsInfo(newsView, new OnDataListener() {
                    @Override
                    public void Success(Object res) {
                        page++;
                        info = (NewsInfo) res;
                        minfo = info.getList();
                        mAdapter.appendData(minfo);
                        callback.onSuccess();
                    }

                    @Override
                    public void Failed(String e) {
                        page--;
                        Toast.makeText(getContext(),e,Toast.LENGTH_SHORT).show();
                        callback.onFailure();
                    }
                });

            }
        });
        rvNewslist.setAdapter(adapter);
        rvNewslist.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getPage() {
        return page+"";
    }

    @Override
    public String getType() {
        return type.value;
    }

    @Override
    public String getLimit() {
        return 10 + "";
    }
}
