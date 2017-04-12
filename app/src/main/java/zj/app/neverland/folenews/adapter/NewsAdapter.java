package zj.app.neverland.folenews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zj.app.neverland.folenews.R;
import zj.app.neverland.folenews.base.BaseAdapter;
import zj.app.neverland.folenews.mvp.model.NewsInfo;

/**
 * Created by zj on 2017/4/1.
 */

public class NewsAdapter extends BaseAdapter<NewsInfo.NewInfo> implements View.OnClickListener{
    private Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , Object data);
    }
    public NewsAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_news,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((ViewHolder) holder).bind(getDataSet().get(position));
        try {
            NewsInfo.NewInfo info = getDataSet().get(position);
            String time = info.getTime();
            String content = info.getTitle();
            String imgurl = info.getImgurl();
            ((ViewHolder) holder).tv_time.setText(time);
            ((ViewHolder) holder).tv_content.setText(content);
            Picasso.with(context)
                    .load(imgurl)
                    .resize(70, 70)
                    .centerCrop()
                    .into(((ViewHolder) holder).imageView);
            ((ViewHolder) holder).itemView.setTag(info);
        }catch (Exception ex){

        }


    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
        ImageView imageView;
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            imageView = (ImageView)itemView.findViewById(R.id.iv_picther);
        }
    }
}
