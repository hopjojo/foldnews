package zj.app.neverland.folenews.bookmark;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zj.app.neverland.folenews.R;
import zj.app.neverland.folenews.base.SharedPreferencesUtils;
import zj.app.neverland.folenews.mvp.model.NewsInfo;
import zj.app.neverland.folenews.web.ShowWebViewActivity;

/**
 * Created by cefoc on 2017/4/7.
 */

public class BookMarkActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_savenews)
    ListView listSavenews;
    private List<NewsInfo.NewInfo> mlist = new ArrayList<>();
    private BookMarkAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        ButterKnife.bind(this);
        initState();

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        mAdapter = new BookMarkAdapter(this, mlist);
        listSavenews.setAdapter(mAdapter);
        listSavenews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsInfo.NewInfo info = mlist.get(position);
                ShowWebViewActivity.getInstance(getApplication().getApplicationContext(), info);
            }
        });
    }
    /**
     * 沉浸式状态栏
     */
    private void initState() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferencesUtils sp = new SharedPreferencesUtils(this);
        mlist = sp.getDataList("info", NewsInfo.NewInfo.class);
        mAdapter.update(mlist);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class BookMarkAdapter extends BaseAdapter {
        private List<NewsInfo.NewInfo> infolist = new ArrayList<>();
        private Context mContext;

        public BookMarkAdapter(Context context, List<NewsInfo.NewInfo> mlist) {
            this.infolist = mlist;
            this.mContext = context;
        }

        public void update(List<NewsInfo.NewInfo> mlist){
            this.infolist.clear();
            this.infolist.addAll(mlist);
            this.notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return infolist.size();
        }

        @Override
        public Object getItem(int position) {
            return infolist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_news, null);
                holder = new ViewHolder();
                holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv_picther);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_content.setText(mlist.get(position).getTitle());
            holder.tv_time.setText(mlist.get(position).getTime());
            Picasso.with(mContext)
                    .load(mlist.get(position).getImgurl())
                    .resize(70, 70)
                    .centerCrop()
                    .into(((ViewHolder) holder).imageView);
            return convertView;
        }

        class ViewHolder {
            TextView tv_content;
            ImageView imageView;
            TextView tv_time;
        }
    }
}
