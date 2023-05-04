package com.example.rss_news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rss_news.Model.Adapters.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        initView();
        SetViewPage();
    }

    private void SetViewPage() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    //check xem đang ở fragment nào thì bottom sáng
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_favorites).setChecked(true);
                        break;
//                    case 2:
//                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_account).setChecked(true);
//                        break;
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_tab_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.menu_tab_favorites:
                        viewPager2.setCurrentItem(1);
                        break;
//                    case R.id.menu_tab_account:
//                        viewPager2.setCurrentItem(2);
//                        break;
                }
                return true;
            }
        });
    }
    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavView);
        viewPager2 = findViewById(R.id.ViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        //Số fragment load trước
        viewPager2.setOffscreenPageLimit(1);
    }
}