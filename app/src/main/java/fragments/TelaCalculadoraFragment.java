package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.joaoc.rigsmonitor.R;
import java.util.ArrayList;
import adapters.CalculatorAdapter;
import interfaces.CalculatorServiceInterface;
import pojo.CalculatorDataWorker;
import services.CalculatorService;

/**
 * Created by joaoc on 02/01/2018.
 */

public class TelaCalculadoraFragment extends Fragment implements CalculatorServiceInterface{

    private ArrayList<CalculatorDataWorker> calculatorDataWorkers;
    private CalculatorAdapter calculatorAdapter;
    private ListView listView;

    public void refreshFragment(){

        calculatorAdapter = null;
        CalculatorService calculatorService = new CalculatorService(getActivity(),this, null);
        calculatorService.start();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_telacalculadora, container, false);

    }

    @Override
    public void getCalculatorDataWorker(CalculatorDataWorker calculatorDataWorker) {

        if(calculatorDataWorker == null){
            Log.d("Saida","getArrayDatas é null");
        }
        else{

            if (calculatorAdapter == null){

                calculatorDataWorkers = new ArrayList<>();
                calculatorDataWorkers.add(calculatorDataWorker);
                calculatorAdapter = new CalculatorAdapter(getActivity(),calculatorDataWorkers);
                listView = (ListView) getActivity().findViewById(R.id.list_calculator);
                listView.setAdapter(calculatorAdapter);

            }else{

                calculatorDataWorkers.add(calculatorDataWorker);
                calculatorAdapter = new CalculatorAdapter(getActivity(),calculatorDataWorkers);
                listView = (ListView) getActivity().findViewById(R.id.list_calculator);
                listView.setAdapter(calculatorAdapter);
                calculatorAdapter.notifyDataSetChanged();

            }

        }

    }
}
