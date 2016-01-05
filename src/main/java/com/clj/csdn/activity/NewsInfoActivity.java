package com.clj.csdn.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.clj.csdn.R;
import com.android.volley.VolleyError;
import com.clj.csdn.app.MyApplication;
import com.clj.csdn.bean.NewsDetail;
import com.clj.csdn.csdn.CsdnNewsParse;
import com.clj.csdn.view.HtmlFrame;
import com.clj.csdn.volley.VolleyClient;
import com.clj.csdn.volley.VolleyResponse;

/**
 * Created by on 2015/10/24.
 */
public class NewsInfoActivity extends BaseActivity implements View.OnClickListener {
    private WebView mWebView;
    private NewsDetail mNews = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Toolbar mToolbar;
    private WebSettings mWebSettings;
    private TextView mTvComments;
    private String mUrl;
    private String mComments;
    private final String mTag = "NewsInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsinfo);
        initView();
        getNewsDetial(200, 200);
    }

    private void initView() {
        //toolbar
        mToolbar = (Toolbar) findViewById(R.id.newsinfo_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //webview
        mWebView = (WebView) findViewById(R.id.newsinfo_webview);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.newsinfo_refreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color1, R.color.swiperefresh_color2, R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsDetial(200, 200);
            }
        });
        // Runnable为了能够第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mTvComments = (TextView) findViewById(R.id.newsinfo_comments);
        mTvComments.setOnClickListener(this);
        //设置标题
        mComments = getIntent().getStringExtra("comments");
        if (mComments != null) {
            mTvComments.setText(mComments);
        }
        //url
        mUrl = getIntent().getStringExtra("url");
    }

    private void getNewsDetial(final int width, final int height) {
        if (mUrl == null) {
            return;
        }
        VolleyClient.RequestGet(NewsInfoActivity.this, mUrl, mTag, new VolleyResponse() {
            @Override
            public void onSuccess(String result) {
                mNews = CsdnNewsParse.getmInstance().getNewsDetial(result, width, height);
                if (mNews == null) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(formatHtml(HtmlFrame.FRAME, mNews.getTitle(), mNews.getInfor(), mNews.getTexts()));
//                stringBuffer.append(HtmlFrame.body);
                mWebView.loadData(stringBuffer.toString(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    /**
     * 格式化html
     *
     * @param frame 框架
     * @param title 标题
     * @param infor 作者时间
     * @param texts 内容
     * @return
     */
    private String formatHtml(String frame, String title, String infor, String texts) {
        return String.format(frame, title, infor, texts);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_newsinfo_comments:
//                //评论
//                break;
            case R.id.menu_newsinfo_share:
                //分享
                break;
            case R.id.menu_newsinfo_font:
                //字体
                setFont();
                break;
        }
        return true;
    }

    public boolean onKeyDown(int keyCoder, KeyEvent event) {
        if (mWebView.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack(); // goBack()表示返回webView的上一页面

            return true;
        }
        return super.onKeyDown(keyCoder, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getmRequestQueue().cancelAll(mTag);
    }

    /**
     * 设置webview的字体
     */
    private void setFont() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewsInfoActivity.this);
        builder.setTitle("选择合适的大小:");
        builder.setSingleChoiceItems(new String[]{"最小", "小", "正常", "大", "最大"}, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mWebSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;
                    case 1:
                        mWebSettings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 2:
                        mWebSettings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        mWebSettings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 4:
                        mWebSettings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                }

            }

        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        if (v == mTvComments) {
            Intent intent = new Intent(NewsInfoActivity.this, CommentsActivity.class);
            intent.putExtra("url", mUrl);
            startActivity(intent);
        }
    }


    //    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.newsinfo_menu, menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//
//    @Override
//    public boolean onCreatePanelMenu(int featureId, Menu menu) {
//        return super.onCreatePanelMenu(featureId, menu);
//    }
}
