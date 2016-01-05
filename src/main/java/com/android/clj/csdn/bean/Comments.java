package com.android.clj.csdn.bean;

import java.util.List;

/**
 * 评论列表
 * Created by clj on 2015/10/30.
 */
public class Comments {


    /**
     * status : 100
     * count : 2
     * reply_count : 4
     * data : [{"id":"437720","create_time":"5分钟前","parent_id":"0","username":"u013920681","body":"阿萨德","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"1","is_markdown":"0","avatar":"http://avatar.csdn.net/1/E/F/3_u013920681.jpg","user_page":"http://my.csdn.net/u013920681","timestamp":"1446279585","children":[{"id":"437722","create_time":"3分钟前","parent_id":1,"username":"u013920681","body":"a","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"0","is_markdown":"0","avatar":"http://avatar.csdn.net/1/E/F/3_u013920681.jpg","user_page":"http://my.csdn.net/u013920681","timestamp":"1446279714"}]},{"id":"437575","create_time":"18小时前","parent_id":"0","username":"qq_24327033","body":"我就说一句：一句","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"3","is_markdown":"0","avatar":"http://avatar.csdn.net/F/7/C/3_qq_24327033.jpg","user_page":"http://my.csdn.net/qq_24327033","timestamp":"1446212845","children":[{"id":"437673","create_time":"4小时前","parent_id":1,"username":"qq_24669321","body":"我就说一句:一句","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"0","is_markdown":"0","avatar":"http://avatar.csdn.net/C/0/F/3_qq_24669321.jpg","user_page":"http://my.csdn.net/qq_24669321","timestamp":"1446263221"},{"id":"437718","create_time":"7分钟前","parent_id":1,"username":"u013920681","body":"啊啊啊啊","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"0","is_markdown":"0","avatar":"http://avatar.csdn.net/1/E/F/3_u013920681.jpg","user_page":"http://my.csdn.net/u013920681","timestamp":"1446279481"},{"id":"437719","create_time":"4分钟前","parent_id":1,"username":"u013920681","body":"啊","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"0","is_markdown":"0","avatar":"http://avatar.csdn.net/1/E/F/3_u013920681.jpg","user_page":"http://my.csdn.net/u013920681","timestamp":"1446279622"}]}]
     */

    private int status;
    private String count;
    private String reply_count;
    /**
     * id : 437720
     * create_time : 5分钟前
     * parent_id : 0
     * username : u013920681
     * body : 阿萨德
     * user_info :
     * content_id : 0
     * from : 0
     * like_count : 0
     * like_people :
     * dislike_count : 0
     * dislike_people :
     * reply_count : 1
     * is_markdown : 0
     * avatar : http://avatar.csdn.net/1/E/F/3_u013920681.jpg
     * user_page : http://my.csdn.net/u013920681
     * timestamp : 1446279585
     * children : [{"id":"437722","create_time":"3分钟前","parent_id":1,"username":"u013920681","body":"a","user_info":"","content_id":"0","from":"0","like_count":"0","like_people":"","dislike_count":"0","dislike_people":"","reply_count":"0","is_markdown":"0","avatar":"http://avatar.csdn.net/1/E/F/3_u013920681.jpg","user_page":"http://my.csdn.net/u013920681","timestamp":"1446279714"}]
     */

    private List<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getCount() {
        return count;
    }

    public String getReply_count() {
        return reply_count;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String create_time;
        private String parent_id;
        private String username;
        private String body;
        private String user_info;
        private String content_id;
        private String from;
        private String like_count;
        private String like_people;
        private String dislike_count;
        private String dislike_people;
        private String reply_count;
        private String is_markdown;
        private String avatar;
        private String user_page;
        private String timestamp;
        /**
         * id : 437722
         * create_time : 3分钟前
         * parent_id : 1
         * username : u013920681
         * body : a
         * user_info :
         * content_id : 0
         * from : 0
         * like_count : 0
         * like_people :
         * dislike_count : 0
         * dislike_people :
         * reply_count : 0
         * is_markdown : 0
         * avatar : http://avatar.csdn.net/1/E/F/3_u013920681.jpg
         * user_page : http://my.csdn.net/u013920681
         * timestamp : 1446279714
         */

        private List<ChildrenEntity> children;

        public void setId(String id) {
            this.id = id;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setUser_info(String user_info) {
            this.user_info = user_info;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public void setLike_people(String like_people) {
            this.like_people = like_people;
        }

        public void setDislike_count(String dislike_count) {
            this.dislike_count = dislike_count;
        }

        public void setDislike_people(String dislike_people) {
            this.dislike_people = dislike_people;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public void setIs_markdown(String is_markdown) {
            this.is_markdown = is_markdown;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setUser_page(String user_page) {
            this.user_page = user_page;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public void setChildren(List<ChildrenEntity> children) {
            this.children = children;
        }

        public String getId() {
            return id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getParent_id() {
            return parent_id;
        }

        public String getUsername() {
            return username;
        }

        public String getBody() {
            return body;
        }

        public String getUser_info() {
            return user_info;
        }

        public String getContent_id() {
            return content_id;
        }

        public String getFrom() {
            return from;
        }

        public String getLike_count() {
            return like_count;
        }

        public String getLike_people() {
            return like_people;
        }

        public String getDislike_count() {
            return dislike_count;
        }

        public String getDislike_people() {
            return dislike_people;
        }

        public String getReply_count() {
            return reply_count;
        }

        public String getIs_markdown() {
            return is_markdown;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getUser_page() {
            return user_page;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public List<ChildrenEntity> getChildren() {
            return children;
        }

        public static class ChildrenEntity {
            private String id;
            private String create_time;
            private int parent_id;
            private String username;
            private String body;
            private String user_info;
            private String content_id;
            private String from;
            private String like_count;
            private String like_people;
            private String dislike_count;
            private String dislike_people;
            private String reply_count;
            private String is_markdown;
            private String avatar;
            private String user_page;
            private String timestamp;

            public void setId(String id) {
                this.id = id;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public void setUser_info(String user_info) {
                this.user_info = user_info;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public void setLike_people(String like_people) {
                this.like_people = like_people;
            }

            public void setDislike_count(String dislike_count) {
                this.dislike_count = dislike_count;
            }

            public void setDislike_people(String dislike_people) {
                this.dislike_people = dislike_people;
            }

            public void setReply_count(String reply_count) {
                this.reply_count = reply_count;
            }

            public void setIs_markdown(String is_markdown) {
                this.is_markdown = is_markdown;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setUser_page(String user_page) {
                this.user_page = user_page;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getId() {
                return id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public int getParent_id() {
                return parent_id;
            }

            public String getUsername() {
                return username;
            }

            public String getBody() {
                return body;
            }

            public String getUser_info() {
                return user_info;
            }

            public String getContent_id() {
                return content_id;
            }

            public String getFrom() {
                return from;
            }

            public String getLike_count() {
                return like_count;
            }

            public String getLike_people() {
                return like_people;
            }

            public String getDislike_count() {
                return dislike_count;
            }

            public String getDislike_people() {
                return dislike_people;
            }

            public String getReply_count() {
                return reply_count;
            }

            public String getIs_markdown() {
                return is_markdown;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getUser_page() {
                return user_page;
            }

            public String getTimestamp() {
                return timestamp;
            }
        }
    }
}
