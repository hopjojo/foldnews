package zj.app.neverland.folenews.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zj on 2017/4/1.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected List<T> dataSet = new ArrayList<>();

    public void updateData(List dataSet) {
        this.dataSet.clear();
        appendData(dataSet);
    }
    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.dataSet.addAll(dataSet);
            notifyDataSetChanged();
        }
    }

    public List<T> getDataSet() {
        return dataSet;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}