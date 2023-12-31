package com.studyproject.studystudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.studyproject.studystudio.language.LanguageActivity;
import com.studyproject.studystudio.utils.StatusBarUtil;
import com.studyproject.studystudio.view.MovingImageView;
import com.studyproject.studystudio.view.MovingViewAnimator;

import org.intellij.lang.annotations.Language;


/**
 * Created by Jaeger on 16/2/14.
 *
 * Email: chjie.jaeger@gmail.com
 * GitHub: https://github.com/laobie
 */
public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ViewGroup contentLayout;
    private int mStatusBarColor;
    MovingImageView movingImageView;
    NavigationView navigationView;
    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTransparent(this);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        contentLayout = findViewById(R.id.main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        navigationView=findViewById(R.id.navigation);
        movingImageView = navigationView.getHeaderView(0).findViewById(R.id.movingImageView);
        // 设置左上角图标["三" —— "←"]效果
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 设置不允许 NavigationMenuView 滚动
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_camera) {
            } else if (itemId == R.id.nav_gallery) {
            } else if (itemId == R.id.nav_slideshow) {
            } else if (itemId == R.id.nav_manage) {
                Intent intent=new Intent(this, LanguageActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_share) {
            } else if (itemId == R.id.nav_send) {
            }
            item.setCheckable(false);
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

       // 添加DrawerLayout监听器，这里根据DrawerLayout的回调方法实现HeaderView的动画效果
       mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                movingImageView.pauseMoving();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                if (movingImageView.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    movingImageView.startMoving();
                } else if (movingImageView.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    movingImageView.resumeMoving();
                }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                movingImageView.stopMoving();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (movingImageView.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    movingImageView.startMoving();
                } else if (movingImageView.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    movingImageView.resumeMoving();
                }
            }
        });
        contentLayout.setBackground(null);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        StatusBarUtil.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, ContextCompat.getColor(this,R.color.colorPrimary), mAlpha);
        StatusBarUtil.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, mStatusBarColor, mAlpha);
    }
    @Override
    protected void setStatusBar() {
        mStatusBarColor = ContextCompat.getColor(this,R.color.colorPrimary);
        StatusBarUtil.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer_layout), mStatusBarColor, mAlpha);
    }
}
