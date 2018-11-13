package com.example.victor.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    public Bus BUS; // I declared it as a variable for easy reference. Not sure if it needs to be public or private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BUS = BusProvider.getInstance();
        BUS.register(this); //You must register with the BUS to produce or subscribe but not to post

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BUS.post(sendCharacter());


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            Fragment frag = new HomeFragment();
            android.app.FragmentManager fragManager = getFragmentManager();
            fragManager.beginTransaction().replace(R.id.fragment_container, frag).commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
            //        new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Fragment frag = new HomeFragment();
                android.app.FragmentManager fragManager = getFragmentManager();
                fragManager.beginTransaction().replace(R.id.fragment_container, frag).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //        new HomeFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatsFragment()).commit();
                break;
            case R.id.nav_inventory:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InventoryFragment()).commit();
                break;
            case R.id.nav_spellbook:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SpellbookFragment()).commit();
                break;
            case R.id.nav_dice:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DiceFragment()).commit();
                break;
            case R.id.nav_items:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ItembookFragment()).commit();
                //Toast.makeText(this, "send", Toast.LENGTH_SHORT).show(); for reference only
                break;
            case R.id.nav_spells:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SpellsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Produce functions for a given even class are called when a different
    // a class registers with the Bus. They are required for dynamically created things
    // such as new fragments or activities.
    @Produce
    public String sendCharacter ()
    {
        return "Producing from Main Activity";
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
