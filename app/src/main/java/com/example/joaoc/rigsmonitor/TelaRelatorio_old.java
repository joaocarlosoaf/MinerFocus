package com.example.joaoc.rigsmonitor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.DataAdapter;
import interfaces.DataServiceInterface;
import pojo.Data;
import pojo.Worker;
import services.DatasService;

/**
 * Created by joaoc on 11/11/2017.
 */

public class TelaRelatorio_old extends AppCompatActivity implements DataServiceInterface {

    //private Toolbar toolbar;
    private DataAdapter dataAdapter;
    private List<Data> datas;
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayList<Worker> arrayList2;
    private ArrayAdapter arrayAdapterWorkers;
    private String url = "https://api.nanopool.org/v1/eth/user/0xf14c49c427f3006f445ca6c064f5829cdf896170";

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_telarelatorio);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_telarelatorio);
        setSupportActionBar(toolbar);
        //Log.d("TelaRelatorioFragment.class","onCreate()");
        showDatas();
    }

    private void showDatas(){

        dialog = new ProgressDialog(this);
        //DatasService datasService = new DatasService(this, this, dialog);
        //datasService.start();

        /*
        workers = new ArrayList<>();
        arrayList = new ArrayList<String>();
        arrayList.add("23");
        arrayList.add("25");
        arrayList.add("26");
        arrayList.add("28");
        arrayList.add("28");

        workers.add(new Worker("teste","teste","teste","teste","teste","teste",arrayList,arrayList2));
        workerAdapter = new WorkerAdapter(this, workers);

        listView = (ListView) findViewById(R.id.list_workers);
        listView.setAdapter(workerAdapter);
        */

    }
//Arrumar o Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Log.d("AQUI::TelaRelatorioFragment::","onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tela_relatorio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                showDatas();
                return true;

            case R.id.action_insert_wallet:

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


    // Interface criada para chamar o método de criar a view após a método principal ter sido executado
    @Override
    public void getArrayDatas(Data data) {

        if(data == null){
            Log.d("Saida","getArrayDatas é null");
        }
        else {
            datas = new ArrayList<>();
            datas.add(data);
            dataAdapter = new DataAdapter(this, datas);
            listView = (ListView) findViewById(R.id.list_workers);
            listView.setAdapter(dataAdapter);
        }




    }
}
