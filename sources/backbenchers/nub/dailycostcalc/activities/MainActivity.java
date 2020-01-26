package backbencers.nub.dailycostcalc.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.widget.DrawerLayout;
import android.support.p003v7.app.ActionBarDrawerToggle;
import android.support.p003v7.app.AppCompatActivity;
import android.support.p003v7.widget.Toolbar;
import android.view.MenuItem;
import backbencers.nub.dailycostcalc.C0374R;
import backbencers.nub.dailycostcalc.constant.CalendarCollection;
import backbencers.nub.dailycostcalc.fragments.AboutFragment;
import backbencers.nub.dailycostcalc.fragments.BalanceFragment;
import backbencers.nub.dailycostcalc.fragments.CreditFragment;
import backbencers.nub.dailycostcalc.fragments.DebitFragment;
import backbencers.nub.dailycostcalc.fragments.HelpFragment;
import backbencers.nub.dailycostcalc.fragments.HistoryFragment;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private Context context;
    private FragmentManager manager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0374R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(C0374R.C0376id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;
        DrawerLayout drawer = (DrawerLayout) findViewById(C0374R.C0376id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, C0374R.string.navigation_drawer_open, C0374R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(C0374R.C0376id.nav_view)).setNavigationItemSelectedListener(this);
        CalendarCollection.date_collection_arr = new ArrayList<>();
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-04-01", "John Birthday"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-04-04", "Client Meeting at 5 p.m."));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-04-06", "A Small Party at my office"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-05-02", "Marriage Anniversary"));
        CalendarCollection.date_collection_arr.add(new CalendarCollection("2015-04-11", "Live Event and Concert of sonu"));
        setFragment(C0374R.C0376id.nav_debit);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(C0374R.C0376id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        setFragment(item.getItemId());
        ((DrawerLayout) findViewById(C0374R.C0376id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    private void setFragment(int id) {
        if (id == C0374R.C0376id.nav_debit) {
            DebitFragment debitFragment = new DebitFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, debitFragment, debitFragment.getTag()).commit();
        } else if (id == C0374R.C0376id.nav_credit) {
            CreditFragment creditFragment = new CreditFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, creditFragment, creditFragment.getTag()).commit();
        } else if (id == C0374R.C0376id.nav_balance) {
            BalanceFragment balanceFragment = new BalanceFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, balanceFragment, balanceFragment.getTag()).commit();
        } else if (id == C0374R.C0376id.nav_history) {
            HistoryFragment historyFragment = new HistoryFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, historyFragment, historyFragment.getTag()).commit();
        } else if (id == C0374R.C0376id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, aboutFragment, aboutFragment.getTag()).commit();
        } else if (id == C0374R.C0376id.nav_help) {
            HelpFragment helpFragment = new HelpFragment();
            this.manager = getSupportFragmentManager();
            this.manager.beginTransaction().replace(C0374R.C0376id.rlContent, helpFragment, helpFragment.getTag()).commit();
        }
    }
}
