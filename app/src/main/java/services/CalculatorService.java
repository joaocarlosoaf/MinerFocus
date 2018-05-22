package services;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import apis.ApiNanopool;
import interfaces.ApiNanopoolCalculatorInterface;
import interfaces.CalculatorServiceInterface;
import pojo.CalculatorDataWorker;

/**
 * Created by joaoc on 06/01/2018.
 */

public class CalculatorService implements ApiNanopoolCalculatorInterface {

    private ApiNanopool _apiNanopool;
    private WalletManipulatorFile _walletManipulatorFile;
    private CalculatorDataWorker _calculatorDataWorker;
    private ProgressDialog progressDialog;
    private CalculatorServiceInterface _calculatorServiceInterface;
    private Context _context;

    public CalculatorService(Context context, CalculatorServiceInterface calculatorServiceInterface, ProgressDialog progressDialog){

        this._context = context;
        this._calculatorServiceInterface = calculatorServiceInterface;
        this.progressDialog = progressDialog;

    }

    public void start(){

        this._apiNanopool = new ApiNanopool();

        this._walletManipulatorFile = new WalletManipulatorFile(_context);

        ArrayList<String> walletsNanopool = _walletManipulatorFile.getWalletsNanoPool();
        ArrayList<String> walletsEthermine = _walletManipulatorFile.getWalletsEthermine();

        if(walletsNanopool != null){

            for(int i  = 0 ; i < walletsNanopool.size() ; i++){

                Log.d("CalculatorService.start","walletsNanopool.get(i) = " + walletsNanopool.get(i));
                this._apiNanopool.getWorkersLastReportedHashrate(this._context, this, walletsNanopool.get(i) );

            }
        }


    }

    private void createListCalculator(String json, String wallet, String hashrate){
        try {
            JSONObject jsonObject = new JSONObject(json);

            String[] hours = new String[2];
            String[] days = new String[2];
            String[] weeks = new String[2];
            String[] months = new String[2];

            if(jsonObject.isNull("status"))
                this._calculatorServiceInterface.getCalculatorDataWorker(null);
            else{

                Log.d("Indice::Comprimento",""+jsonObject.length());
                Log.d("SAIDA::data",""+jsonObject.getString("data"));

                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                JSONObject jsonObjectHour = jsonObjectData.getJSONObject("hour");
                JSONObject jsonObjectDay = jsonObjectData.getJSONObject("day");
                JSONObject jsonObjectWeek = jsonObjectData.getJSONObject("week");
                JSONObject jsonObjectMonth = jsonObjectData.getJSONObject("month");

                hours[0] = jsonObjectHour.getString("bitcoins").substring(0,8);
                hours[1] = jsonObjectHour.getString("dollars").substring(0,8);

                days[0] = jsonObjectDay.getString("bitcoins").substring(0,8);
                days[1] = jsonObjectDay.getString("dollars").substring(0,8);


                weeks[0] = jsonObjectWeek.getString("bitcoins").substring(0,8);
                weeks[1] = jsonObjectWeek.getString("dollars").substring(0,8);


                months[0] = jsonObjectMonth.getString("bitcoins").substring(0,8);
                months[1] = jsonObjectMonth.getString("dollars").substring(0,8);

                String hashrateCurrent = hashrate;

                this._calculatorDataWorker = new CalculatorDataWorker(wallet,hashrateCurrent,hours[0],hours[1],days[0],days[1],weeks[0],weeks[1],months[0],months[1]);

                this._calculatorServiceInterface.getCalculatorDataWorker(this._calculatorDataWorker);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String sumHashrateByWorkers(String json){

        //ArrayList<String> hashrates = new ArrayList<>();
        double sumHarshrate = 0;

        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.isNull("status"))
                return null;
            else {

                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i = 0 ; i < jsonArray.length() ; i++){

                    JSONObject objectHashrate = jsonArray.getJSONObject(i);
                    sumHarshrate += Double.parseDouble(String.valueOf(objectHashrate.getString("hashrate")));
                    //hashrates.add(objectHashrate.getString("hashrate"));

                }


                return String.valueOf(sumHarshrate);
            }

        }catch(JSONException e){
                e.printStackTrace();
            }

        return null;
    }

    @Override
    public void getWorkersLastReportedHashrate(String json, String wallet) {

        Log.d("getWorkersLastReported","wallet Json = "+ wallet);
        this._apiNanopool.getHashRateCalculator(this._context, this, wallet, sumHashrateByWorkers(json) );


    }

    @Override
    public void getHashRateCalculator(String json, String wallet, String hashrate) {

        createListCalculator(json, wallet, hashrate);

    }
}
