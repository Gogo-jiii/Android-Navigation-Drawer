package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        showFirstFragment();
        setupNavigationDrawer();
    }

    private void showFirstFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, new Fragment1());
        ft.commit();
    }

    private void setupNavigationDrawer() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override public void onPostCreate(@Nullable Bundle savedInstanceState,
                                       @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_item_one:
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                toolbar.setTitle("Fragment 1");
                fragment = new Fragment1();
                break;
            case R.id.nav_item_two:
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                toolbar.setTitle("Fragment 2");
                fragment = new Fragment2();
                break;
            case R.id.nav_item_three:
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                toolbar.setTitle("Fragment 3");
                fragment = new Fragment3();
                break;
            case R.id.nav_item_four:
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_five:
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_six:
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_item_seven:
                Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}