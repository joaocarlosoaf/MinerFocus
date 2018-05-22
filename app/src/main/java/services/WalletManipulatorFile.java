package services;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by joaoc on 29/03/2018.
 */

public class WalletManipulatorFile {

    private Context _context;
    private FileManipulator _fileManipulator;

    public WalletManipulatorFile(Context context){

        this._context = context;
        this._fileManipulator = new FileManipulator(this._context);

    }

    public ArrayList<String> getWalletsNanoPool(){

        ArrayList<String> wallets =  new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject((String)this._fileManipulator.readFile('n'));

            Log.d("getWalletsNanoPool","jsonObject = "+jsonObject.toString());

            JSONArray jsonArray = jsonObject.getJSONArray("wallets");

            for (int i = 0 ; i < jsonArray.length() ; i++){

                wallets.add(jsonArray.getString(i));

            }

            return wallets;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return wallets;
    }

    public ArrayList<String> getWalletsEthermine(){

        ArrayList<String> wallets =  new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject((String)this._fileManipulator.readFile('e'));

            JSONArray jsonArray = jsonObject.getJSONArray("wallets");

            for (int i = 0 ; i < jsonArray.length() ; i++){

                wallets.add(jsonArray.getString(i));

            }

            return wallets;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return wallets;
    }

    public void deleteWallet(String wallet, char c){

        try{

            ArrayList<String> list = new ArrayList<String>();
            // c é referente ao arquivo da carteira, e = ethermine; n = nanopool
            JSONObject jsonObject = new JSONObject((String)this._fileManipulator.readFile(c));
            JSONArray jsonArray = jsonObject.getJSONArray("wallets");

            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){

                    if(!jsonArray.get(i).equals(wallet))
                        list.add(jsonArray.get(i).toString());

                }

            }

            JSONArray jsArray = new JSONArray(list);
            jsonObject = new JSONObject();
            jsonObject.put("wallets", jsArray);
            this._fileManipulator.writeFile(jsonObject.toString(), c);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void insertWallet(String wallet, char c){

        String name = (c == 'n') ? "nanopool" : "ethermine";

        Log.d("name","name = "+ name);

        try{

            // c é referente ao arquivo da carteira, e = ethermine; n = nanopool

            String data = (String)this._fileManipulator.readFile(c);

            JSONObject jsonObject;
            if(data.length()>1){
                jsonObject = new JSONObject((String)this._fileManipulator.readFile(c));

                JSONArray jsonArray = jsonObject.getJSONArray("wallets");

                if (jsonArray != null) {
                    jsonArray.put(wallet);
                }

                jsonObject = new JSONObject();
                jsonObject.put("wallets", jsonArray);

            }
            else{
                jsonObject = new JSONObject("{\"wallets\":["+wallet+"]}");
            }

            Log.d("_fileManipulator","readFile = "+data);
            Log.d("jsonObject","jsonObject = "+ jsonObject.toString());
            this._fileManipulator.writeFile(jsonObject.toString(), c);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
