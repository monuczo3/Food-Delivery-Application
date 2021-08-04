package com.monu.threemeals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.monu.threemeals.fragments.FAQFragment;
import com.monu.threemeals.fragments.FavouriteRestaurantsFragment;
import com.monu.threemeals.fragments.HomeFragment;
import com.monu.threemeals.fragments.LogoutFragment;
import com.monu.threemeals.fragments.MyProfileFragment;
import com.monu.threemeals.fragments.OrderHistoryFragment;

public class DashboardActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    TextView Name;
    TextView Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigation);
        coordinatorLayout = findViewById(R.id.Coordinator_layout);
        frameLayout = findViewById(R.id.frame);

        setupNavigationHeaderNamePhone();

        setuptoolbar();

        setuphomefragment();

        setUpDrawerContent(navigationView);

        toggle = new ActionBarDrawerToggle(DashboardActivity.this,drawerLayout,toolbar,
                R.string.open_drawer,R.string.close_drawer);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */
    }

    private void setupNavigationHeaderNamePhone() {
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        View hView = navigationView.getHeaderView(0);
        Name = hView.findViewById(R.id.tv_name);
        Phone = hView.findViewById(R.id.tv_phoneno);
        String name = sh.getString("user_name",null);
        String phone = sh.getString("user_mobile_number",null);
        phone = "+91 " + phone;
        if(name != null && phone != null){
            Name.setText(name.toString());
            Phone.setText(phone);
        }
    }

    public void setuphomefragment() {
        Class FragmentClass;
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) FragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.frame,fragment).commit();
            getSupportActionBar().setTitle("All Restaurants");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    selectDrawerItem(item);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) throws InstantiationException, IllegalAccessException {
        Class fragmentClass;
        Fragment frag ;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()){
            case R.id.Home:
                setuphomefragment();
                setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                break;
            case R.id.MyProfile:
                fragmentClass = MyProfileFragment.class;
                frag = (Fragment) fragmentClass.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame,frag).commit();
                setTitle(item.getTitle());
                getSupportActionBar().setTitle("My Profile");
                drawerLayout.closeDrawers();
                break;
            case R.id.favouriteRestaurant:
                fragmentClass = FavouriteRestaurantsFragment.class;
                frag = (Fragment) fragmentClass.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame,frag).commit();
                setTitle(item.getTitle());
                getSupportActionBar().setTitle("Favourite Restaurants");
                drawerLayout.closeDrawers();
                break;
            case R.id.orderHistory:
                fragmentClass = OrderHistoryFragment.class;
                frag = (Fragment) fragmentClass.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame,frag).commit();
                setTitle(item.getTitle());
                getSupportActionBar().setTitle("Order History");
                drawerLayout.closeDrawers();
                break;
            case R.id.faq:
                fragmentClass = FAQFragment.class;
                frag = (Fragment) fragmentClass.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame,frag).commit();
                setTitle(item.getTitle());
                getSupportActionBar().setTitle("FAQs");
                drawerLayout.closeDrawers();
                break;
            case R.id.logout:
                fragmentClass = LogoutFragment.class;
                frag = (Fragment) fragmentClass.newInstance();
                fragmentManager.beginTransaction().replace(R.id.frame,frag).commit();
                setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                break;
            default:
                break;
        }


    }

    private void setuptoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Restaurants");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.frame);
        Fragment home = getSupportFragmentManager().findFragmentById(R.id.Home);
        if(fragment1 != home){
            setuphomefragment();
        }else{

            super.onBackPressed();
        }

    }
}