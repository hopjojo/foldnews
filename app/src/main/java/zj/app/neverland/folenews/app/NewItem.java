package zj.app.neverland.folenews.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cefoc on 2017/4/1.
 */

public class NewItem implements Serializable{
    public String value;
    public String name;

    public NewItem(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public static List<NewItem> getNewType() {
        List<NewItem> a = new ArrayList<NewItem>();
        a.add(new NewItem("war","军事"));
        a.add(new NewItem("sport","体育"));
        a.add(new NewItem("tech","科技"));
        a.add(new NewItem("edu","教育"));
        a.add(new NewItem("ent","娱乐"));
        a.add(new NewItem("money","财经"));
        a.add(new NewItem("gupiao","股票"));
        a.add(new NewItem("travel","旅游"));
        a.add(new NewItem("lady","女人"));
        return a;
    }
}
