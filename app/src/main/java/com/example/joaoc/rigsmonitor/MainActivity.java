package com.example.joaoc.rigsmonitor;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import adapters.ViewPagerAdapter;
import fragments.TelaCalculadoraFragment;
import fragments.TelaRelatorioFragment;
import popups.WalletManagerDialog;

public class MainActivity extends AppCompatActivity{

    private ViewPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fileManipulator.writeFile(this,"");
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_telarelatorio);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //teste();

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new TelaRelatorioFragment(), "Minerando");
        adapter.addFragment(new TelaCalculadoraFragment(),"Calculadora");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("AQUI::TelaRelatFrag::","onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tela_relatorio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:

                Log.d("SAIDA","viewPager.getCurrentItem()" + viewPager.getCurrentItem());

                Fragment currentFragment = adapter.getItem(viewPager.getCurrentItem());
                if (viewPager.getCurrentItem() == 0){
                    TelaRelatorioFragment telaRelatorioFragment = (TelaRelatorioFragment) currentFragment;
                    telaRelatorioFragment.refreshFragment();
                }
                if (viewPager.getCurrentItem() == 1){

                    TelaCalculadoraFragment telaCalculadoraFragment = (TelaCalculadoraFragment) currentFragment;
                    telaCalculadoraFragment.refreshFragment();
                }

                return true;

            case R.id.action_insert_wallet:
                WalletManagerDialog walletManagerDialog = new WalletManagerDialog(this);
                walletManagerDialog.showDiaologWallet();
                return true;

            case R.id.action_onoff_notification:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

}
