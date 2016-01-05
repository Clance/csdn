package com.clj.csdn.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.clj.csdn.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clj.csdn.adapter.CommentsAdapter;
import com.clj.csdn.app.MyApplication;
import com.clj.csdn.bean.Comments;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends BaseActivity {
    /**
     * 链接地址
     */
    private String mUrl;
    /**
     * 页数
     */
    private int mPageNum = 1;
    /**
     * 个数
     */
    private int mPagesize = 10;
    /**
     * 评论数
     */
    private TextView mTvCommentsNum;

    /**
     * 评论集合
     */
    private List<Comments.DataEntity> mDataEntityList;

    private ExpandableListView mExpandableListView;
    /**
     * 适配器
     */
    private CommentsAdapter mCommentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initView();
        initData();

    }

    private void initView() {
        mTvCommentsNum = (TextView) findViewById(R.id.comments_number);
        mExpandableListView = (ExpandableListView) findViewById(R.id.comments_expandablelistview);
        //设置 属性 GroupIndicator 去掉默认向下的箭头
        mExpandableListView.setGroupIndicator(null);
        mDataEntityList = new ArrayList<>();
        mCommentsAdapter = new CommentsAdapter(this, mDataEntityList);
        mExpandableListView.setAdapter(mCommentsAdapter);

    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        if (url == null) {
            return;
        }
        mUrl = "http://ptcms.csdn.net/comment/comment/newest?url=" + url + "&pageno=" + mPageNum + " &pagesize=" + mPagesize;
//        mUrl ="http://ptcms.csdn.net/comment/comment/newest?url=http://www.csdn.net/article/2015-10-29/2826083&pageno=1%20&pagesize=10";
        JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET, mUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    return;
                }
                Gson gson = new Gson();
                Type type = new TypeToken<Comments>() {
                }.getType();
                Comments comments = gson.fromJson(response.toString(), type);
                String count = comments.getCount();
                if (comments != null) {
                    mTvCommentsNum.setText(count + "条");
                    if (comments.getData() != null) {
                        mDataEntityList = comments.getData();
                        for (Comments.DataEntity dataEntity : comments.getData())
                            Log.i("clj", dataEntity.getBody());
                        mCommentsAdapter.setmCommentsList(mDataEntityList);
                        mCommentsAdapter.notifyDataSetChanged();
                        //将所有项设置成默认展开
                        int groupCount = mExpandableListView.getCount();
                        for (int i = 0; i < groupCount; i++) {
                            mExpandableListView.expandGroup(i);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("clj", error.toString());
            }

        });
        MyApplication.getmRequestQueue().add(arrayRequest);
    }
}
