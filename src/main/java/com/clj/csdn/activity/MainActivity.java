package com.clj.csdn.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.clj.csdn.R;
import com.clj.csdn.adapter.ViewPageFragmentAdapter;
import com.clj.csdn.app.Constraint;
import com.clj.csdn.view.Titanic;
import com.clj.csdn.view.TitanicTextView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private Titanic mTitanic;
    private TitanicTextView mTitanicTextView;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(android.R.color.transparent);//通知栏所需颜色


    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawlayout);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.setOffscreenPageLimit(5);
        setSupportActionBar(mToolbar);


        //关联toolbar导航键和mDrawerLayout
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mTitanic = new Titanic();
                mTitanicTextView = (TitanicTextView) findViewById(R.id.titanic_tv);
                mTitanic.start(mTitanicTextView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mTitanic.cancel();
            }
        };
        mActionBarDrawerToggle.syncState();
        //设置打开关闭的监听事件
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mViewPager.setAdapter(new ViewPageFragmentAdapter(Constraint.CSDN_CATEGORY, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        mNavigationView = (NavigationView) findViewById(R.id.main_navigationview);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_image:
                        Toast.makeText(getApplicationContext(), "asds", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_cache:
//                        final File file = ACache.get(getCacheDir()).file(Constraint.CSDN_CATEGORY[0]).d;
//                        String size = Formatter.formatFileSize(MainActivity.this, file.length());
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setCancelable(true);
                        alertDialog.setTitle("清理").setMessage("缓存大小" ).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                file.delete();
                            }
                        }).show();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("关于");
            builder.setMessage("仅供学习使用，请勿用于其他用途" + "\n" + "\n" + "by clj");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
