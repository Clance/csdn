package com.clj.csdn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clj.csdn.util.ACache;
import com.android.volley.VolleyError;
import com.clj.csdn.R;
import com.clj.csdn.activity.NewsInfoActivity;
import com.clj.csdn.adapter.NewsAdapter;
import com.clj.csdn.app.Constraint;
import com.clj.csdn.bean.News;
import com.clj.csdn.csdn.CsdnNewsParse;
import com.clj.csdn.volley.VolleyClient;
import com.clj.csdn.volley.VolleyErrorHelper;
import com.clj.csdn.volley.VolleyResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 新闻
 * Created by Administrator on 2015/10/23.
 */
public class NewsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<News> mDatas;
    private NewsAdapter mAdapter;
    private int mType;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mManager;
    private int mPage = 1;
    private String mError;
    private ACache mACache;
    private List<News> mCacheList;
    public NewsFragment() {
    }

    public NewsFragment(int type) {
        this.mType = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mCacheList= (List<News>) mACache.getAsObject(Constraint.CSDN_CATEGORY[mType-1]);
        if (mCacheList!=null){
            mAdapter.setmDatas(mCacheList);
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setIsLoading(false);
            mAdapter.setmError(null);
            mAdapter.notifyDataSetChanged();
            Log.i("NewsFragment","cache");
            return;
        }
        getNews(mType, true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View view) {
        mACache=ACache.get(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycleview);
        mManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDatas = new ArrayList<>();
        mAdapter = new NewsAdapter(getActivity(), mDatas);
        mAdapter.setOnItemClickLitener(new NewsAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                intent.putExtra("url", mAdapter.getmDatas().get(position).getLink());
                intent.putExtra("comments", mAdapter.getmDatas().get(position).getNum_recom());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews(mType, true);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int last = mManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && last + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() > 1) {
                    getNews(mType, false);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }


    public void getNews(final int type, final boolean isRefresh) {
        mDatas = new ArrayList<>();
        String url = "";
        url = getUrl(type);
        if (isRefresh) {
            mPage = 1;
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    // Runnable为了能够第一次进入页面的时候显示加载进度条
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        } else {
            mPage++;
            mAdapter.setIsLoading(true);
            mAdapter.notifyDataSetChanged();
        }
        final String finalUrl = url;
        VolleyClient.RequestGet(getActivity(), url + mPage, "q", new VolleyResponse() {
            @Override
            public void onSuccess(String result) {
                mDatas = CsdnNewsParse.getmInstance().getNewsList(result);
                if (isRefresh) {
                    mAdapter.getmDatas().clear();
                    mAdapter.setmDatas(mDatas);
                } else {
                    mAdapter.getmDatas().addAll(mDatas);
                }
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setIsLoading(false);
                mAdapter.setmError(null);
                mAdapter.notifyDataSetChanged();
                mACache.put(Constraint.CSDN_CATEGORY[type-1], (Serializable) mDatas);

            }

            @Override
            public void onError(VolleyError error) {
                mError = VolleyErrorHelper.getMessage(error, getActivity());
                Snackbar.make(mSwipeRefreshLayout, mError, Snackbar.LENGTH_LONG).show();
                mAdapter.setmError(mError);
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setIsLoading(true);
                mAdapter.notifyDataSetChanged();
            }
        });


    }

    private String getUrl(int type) {
        String url="";
        switch (type) {
            case 1:
                url = Constraint.CSDN_NEWS;
                break;
            case 2:
                url = Constraint.CSDN_CLOUD;
                break;
            case 3:
                url = Constraint.CSDN_MOBILE;
                break;
            case 4:
                url = Constraint.CSDN_SOFT;
                break;
            case 5:
                url = Constraint.CSDN_PROGRAMMER;
                break;
        }
        return url;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
