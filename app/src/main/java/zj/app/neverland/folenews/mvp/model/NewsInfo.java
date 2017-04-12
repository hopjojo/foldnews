package zj.app.neverland.folenews.mvp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cefoc on 2017/4/1.
 */

public class NewsInfo {
//    {"size":10,
// "list":[{"
// imgurl":"http://cms-bucket.nosdn.127.net/b5d8b16bbdff495e8adaad0741fc033420170331180812.png",
// "has_content":true,
// "docurl":"http://sports.163.com/17/0331/18/CGSHP6EV00058780.html",
// "id":27589,
// "time":"2017-03-31 18:08:25",
// "title":"互相伤害!黄博文调侃于汉超手球 却遭泼可乐回击",
// "channelname":"sport"}
    private int size;
    private List<NewInfo> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<NewInfo> getList() {
        return list;
    }

    public void setList(List<NewInfo> list) {
        this.list = list;
    }

    public class  NewInfo implements Serializable{
        private String imgurl;
        private boolean has_content;
        private String docurl;
        private int id;
        private String time;
        private String title;
        private String channelname;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public boolean isHas_content() {
            return has_content;
        }

        public void setHas_content(boolean has_content) {
            this.has_content = has_content;
        }

        public String getDocurl() {
            return docurl;
        }

        public void setDocurl(String docurl) {
            this.docurl = docurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannelname() {
            return channelname;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }
    }
}
