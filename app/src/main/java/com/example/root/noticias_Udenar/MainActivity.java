package com.example.root.noticias_Udenar;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Bundle bd;
    DrawerLayout drawer;
    NavigationView nav;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bd = savedInstanceState;
        super.onCreate(bd);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        nav = (NavigationView) findViewById(R.id.nav);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggle = new ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name);

        drawer.setDrawerListener(this);
        nav.setNavigationItemSelectedListener(this);

        loadComponents();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    private void loadComponents() {





        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
        if (id == R.id.action_buscar) {
            Toast.makeText(this,"Actualizando",Toast.LENGTH_LONG).show();
           /* Intent intent = getIntent();
            finish();
            startActivity(intent);*/
            loadComponents();
            return true;
        }

        if(toggle.onOptionsItemSelected(item))
        { return true;}


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        toggle.onDrawerSlide(drawerView,slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        toggle.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        toggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        toggle.onDrawerStateChanged(newState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i = new Intent(this, DetailActivity.class);
        switch (item.getItemId()){
            case R.id.principal:


                i.putExtra("url-extra", "http://www.udenar.edu.co/portal/");
                startActivity(i);

                break;
            case R.id.biblioteca:


                i.putExtra("url-extra", "http://biblioteca.udenar.edu.co/atenea/");
                startActivity(i);

                break;
            case R.id.vipri:


                i.putExtra("url-extra", "http://vipri.udenar.edu.co/investigaciones/");
                startActivity(i);
                break;
            case R.id.matriculas:

                i.putExtra("url-extra", "http://akademica.udenar.edu.co/matricula/");
                startActivity(i);

                break;
            case R.id.horarios:

                i.putExtra("url-extra", "http://apolo.udenar.edu.co/horarios/consulta_horarios1.php");
                startActivity(i);

                break;
            case R.id.notas:
                i.putExtra("url-extra", "http://akademica.udenar.edu.co/consultanotas/");
                startActivity(i);
                break;
            case R.id.competencias:
                i.putExtra("url-extra", "http://www.udenar.edu.co/portal/?page_id=2999");
                startActivity(i);
                break;
            case R.id.acerca_de:
                Intent j = new Intent(this, AcercaDe.class);
                startActivity(j);
                break;


        }

        drawer.closeDrawers();
        return false;
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return Fragment1.newInstance();
                case 1:
                    return Fragment2.newInstance();

            }
            return null;
        }

        @Override
        public int getCount() {

            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "C. Comunicaciones";
                case 1:
                    return "Udenar Periodico";

            }
            return null;
        }
    }
}
