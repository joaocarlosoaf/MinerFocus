package apis;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import interfaces.ApiNanopoolCalculatorInterface;
import interfaces.ApiNanopoolDatasInterface;

/**
 * Created by joaoc on 09/01/2018.
 */

public class ApiNanopool {

    private static final String URL_GENERAL_INFO = "https://api.nanopool.org/v1/eth/user/";
    private static final String URL_WORKERS_LAST_REPORTED_HASHRATE = "https://api.nanopool.org/v1/eth/reportedhashrates/";
    private static final String URL_CALCULATE_HASHRATE = "https://api.nanopool.org/v1/eth/approximated_earnings/";
    private Context context;
    static private String jsonString;


    public String getWorkersAverageHashrates(Context context, final ApiNanopoolDatasInterface apiNanopoolDatasInterface, final String wallet){

        this.context = context;

        Log.d("startDownloadJson::",URL_GENERAL_INFO + wallet);

        RequestQueue rQueue = Volley.newRequestQueue(this.context);

        StringRequest stringRequest = new StringRequest(URL_GENERAL_INFO + wallet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonString = response;
                Log.d("onResponse::",jsonString);
                apiNanopoolDatasInterface.getWorkersAverageHashrates(jsonString, wallet);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiNanopool::onErrorRes","erro:"+error.getMessage());
            }
        });

        rQueue.add(stringRequest);
        return jsonString;
    }

    public String getWorkersLastReportedHashrate(Context context, final ApiNanopoolCalculatorInterface apiNanopoolCalculatorInterface, final String wallet){

        this.context = context;

        Log.d("startDownloadJson::",URL_WORKERS_LAST_REPORTED_HASHRATE + wallet);

        RequestQueue rQueue = Volley.newRequestQueue(this.context);

        StringRequest stringRequest = new StringRequest(URL_WORKERS_LAST_REPORTED_HASHRATE + wallet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonString = response;
                Log.d("onResponse::",jsonString);
                apiNanopoolCalculatorInterface.getWorkersLastReportedHashrate(jsonString, wallet);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiNanopool::onErrorRes","erro:"+error.getMessage());
            }
        });

        rQueue.add(stringRequest);
        return jsonString;

    }

    public String getHashRateCalculator(Context context, final ApiNanopoolCalculatorInterface apiNanopoolCalculatorInterface, final String wallet, final String hashrate){

        this.context = context;

        Log.d("startDownloadJson::",URL_CALCULATE_HASHRATE + hashrate);

        RequestQueue rQueue = Volley.newRequestQueue(this.context);

        StringRequest stringRequest = new StringRequest(URL_CALCULATE_HASHRATE + hashrate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonString = response;
                Log.d("onResponse::",jsonString);
                apiNanopoolCalculatorInterface.getHashRateCalculator(jsonString, wallet, hashrate);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiNanopool::onErrorRes","erro:"+error.getMessage());
            }
        });

        rQueue.add(stringRequest);
        return jsonString;

    }

}
