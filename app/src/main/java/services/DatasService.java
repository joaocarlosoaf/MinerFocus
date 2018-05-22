package services;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import apis.ApiNanopool;
import interfaces.ApiNanopoolDatasInterface;
import interfaces.DataServiceInterface;
import pojo.Data;
import pojo.Worker;

/**
 * Created by joaoc on 17/11/2017.
 * Classe responsável por baixar e montar o JsonObject e retornar Datas(um array de Data) usando Interface
 * "https://api.nanopool.org/v1/eth/user/{...}"
 */

public class DatasService implements ApiNanopoolDatasInterface{

    private ApiNanopool _apiNanopool;
    private WalletManipulatorFile _walletManipulatorFile;
    private Data _data;
    private ArrayList<String> _avghashrate;
    private DataServiceInterface _dataServiceInterface;
    private Context _context;

    public DatasService(Context context, DataServiceInterface dataServiceInterface){

        this._context = context;
        this._dataServiceInterface = dataServiceInterface;

    }

    // Método responsável por iniciar as operações de download, converter para string, criar objeto DATA
    // e retornar o Obejeto DATA para a classe TelaRelatorioFragment atraves da Interface DataServiceInterface
    public void start(){

        this._apiNanopool = new ApiNanopool();
        this._walletManipulatorFile = new WalletManipulatorFile(_context);

        ArrayList<String> walletsNanopool = _walletManipulatorFile.getWalletsNanoPool();
        ArrayList<String> walletsEthermine = _walletManipulatorFile.getWalletsEthermine();

        if(walletsNanopool != null){
            for(int i = 0 ; i < walletsNanopool.size() ; i++){

                _apiNanopool.getWorkersAverageHashrates(_context, this,walletsNanopool.get(i));

            }
        }


    }

    // Recebe JSON em formato String e atribui os valores do JSON para o objeto DATA
    private void createListDatas(String json, String wallet){
        try {
            JSONObject jsonObject = new JSONObject(json);

            this._avghashrate = new ArrayList<>();

            JSONObject jsonObjectData= jsonObject.getJSONObject("data");
            JSONObject jsonObjectAVGHashRate = jsonObjectData.getJSONObject("avgHashrate");

            this._avghashrate.add(jsonObjectAVGHashRate.getString("h24"));
            this._avghashrate.add(jsonObjectAVGHashRate.getString("h12"));
            this._avghashrate.add(jsonObjectAVGHashRate.getString("h6"));
            this._avghashrate.add(jsonObjectAVGHashRate.getString("h3"));
            this._avghashrate.add(jsonObjectAVGHashRate.getString("h1"));

            JSONArray jsonArray = jsonObjectData.getJSONArray("workers");

            this._data = new Data(jsonObjectData.getString("account"),
                    jsonObjectData.getString("balance"),
                    jsonObjectData.getString("hashrate"),
                    this._avghashrate,
                    generateWorkersByJson(jsonArray));

            this._dataServiceInterface.getArrayDatas(_data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Worker> generateWorkersByJson(JSONArray jsonArray){

        ArrayList<Worker> workers = new ArrayList<>();
        ArrayList<String> hashrates = new ArrayList<>();

        for(int i = 0 ; i < jsonArray.length() ; i++){

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                hashrates.add(jsonObject.getString("h1"));
                hashrates.add(jsonObject.getString("h3"));
                hashrates.add(jsonObject.getString("h6"));
                hashrates.add(jsonObject.getString("h12"));
                hashrates.add(jsonObject.getString("h24"));
                workers.add(new Worker(jsonObject.getString("id"), jsonObject.getString("hashrate"), hashrates));
                Log.d("generateWorkersByJson","New worker = " + jsonObject.getString("id") + " " + jsonObject.getString("hashrate") + hashrates);
                hashrates = new ArrayList<>();



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return workers;

    }

    @Override
    public void getWorkersAverageHashrates(String json, String wallet) {

        createListDatas(json, wallet);

    }
}
