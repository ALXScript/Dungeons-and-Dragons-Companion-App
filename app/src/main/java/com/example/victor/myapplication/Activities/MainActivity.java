package com.example.victor.myapplication.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.victor.myapplication.Classes.BusProvider;
import com.example.victor.myapplication.Classes.Character;
import com.example.victor.myapplication.Fragments.DiceFragment;
import com.example.victor.myapplication.Fragments.HomeFragment;
import com.example.victor.myapplication.Fragments.InventoryFragment;
import com.example.victor.myapplication.Fragments.AllItemsFragment;
import com.example.victor.myapplication.R;
import com.example.victor.myapplication.Fragments.SpellbookFragment;
import com.example.victor.myapplication.Fragments.AllSpellsFragment;
import com.example.victor.myapplication.Fragments.CharacterSheetFragment;
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
        BUS.post(sendCharacter());
//        BUS.unregister(this);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                        new CharacterSheetFragment()).commit();

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
                        new AllItemsFragment()).commit();
                //Toast.makeText(this, "send", Toast.LENGTH_SHORT).show(); for reference only
                break;
            case R.id.nav_spells:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllSpellsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Produce functions for a given even class are called when a different
    // a class registers with the Bus. They are required for dynamically created things
    // such as new fragments or activities.
    @Produce
    public Character sendCharacter ()
    {
        String name="Xanandorf";
        int maxHitPoints=38;
        int abilityScores[]={5,8,10,13,20,15};
        boolean skillProficiencies [] = {true,false,true,false,false,true,true,true,false,true,false,true,false,true,true,false,true,false};
        boolean savingThrowProficiencies [] = {true,true,false,false,true,false};
        Character sampleCharacter = new Character(name,maxHitPoints,abilityScores,skillProficiencies,savingThrowProficiencies);

        return sampleCharacter;
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