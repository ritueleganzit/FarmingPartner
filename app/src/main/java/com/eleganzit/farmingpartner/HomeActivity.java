package com.eleganzit.farmingpartner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.eleganzit.farmingpartner.fragments.ContactOfficeFragment;
import com.eleganzit.farmingpartner.fragments.ManageFarmFragment;
import com.eleganzit.farmingpartner.fragments.MyProfileFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AdvanceDrawerLayout drawer;
    public static TextView home_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home_title = findViewById(R.id.textTitle);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_ham, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setViewScale(Gravity.START, 0.9f);
        drawer.setRadius(Gravity.START, 15);
        drawer.setViewElevation(Gravity.START, 20);
        MyProfileFragment myProfileFragment = new MyProfileFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, myProfileFragment, "TAG")
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            MyProfileFragment myProfileFragment = new MyProfileFragment();

            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("NavHomeActivity")
                    .replace(R.id.container, myProfileFragment, "TAG")
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            ManageFarmFragment myProfileFragment = new ManageFarmFragment();

            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("NavHomeActivity")
                    .replace(R.id.container, myProfileFragment, "TAG")
                    .commit();

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(HomeActivity.this,NotificationsActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        } else if (id == R.id.nav_tools) {
            ContactOfficeFragment contactOfficeFragment = new ContactOfficeFragment();

            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("NavHomeActivity")
                    .replace(R.id.container, contactOfficeFragment, "TAG")
                    .commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
