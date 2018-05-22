package fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import com.example.joaoc.rigsmonitor.R;
import java.util.ArrayList;
import java.util.List;
import adapters.DataAdapter;
import adapters.DataExpandableListaAdapter;
import interfaces.DataServiceInterface;
import pojo.Data;
import pojo.Worker;
import services.DatasService;

/**
 * Created by joaoc on 11/11/2017.
 */

public class TelaRelatorioFragment extends Fragment implements DataServiceInterface {

    //private Toolbar toolbar;
    private DataAdapter dataAdapter;
    private List<Data> datas;
    private ListView listView;
    private DataExpandableListaAdapter dataExpandableListaAdapter;
    private ExpandableListView expandableListView;

    public TelaRelatorioFragment(){

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
        return inflater.inflate(R.layout.fragment_telarelatorio, container, false);

    }

    public void refreshFragment(){

        dataExpandableListaAdapter = null;
        DatasService datasService = new DatasService(getActivity(), this);
        datasService.start();

    }

    // Interface criada para chamar o método de criar a view após a método principal ter sido executado
    @Override
    public void getArrayDatas(Data data) {

        if(data == null){
            Log.d("Saida","getArrayDatas é null");
        }

        else {

            if(dataExpandableListaAdapter == null){

                datas = new ArrayList<>();
                datas.add(data);
                dataExpandableListaAdapter = new DataExpandableListaAdapter(getActivity(), datas);
                expandableListView = (ExpandableListView) getView().findViewById(R.id.list_workers);
                expandableListView.setAdapter(dataExpandableListaAdapter);

            }
            else{

                datas.add(data);
                dataExpandableListaAdapter = new DataExpandableListaAdapter(getActivity(), datas);
                expandableListView = (ExpandableListView) getView().findViewById(R.id.list_workers);
                expandableListView.setAdapter(dataExpandableListaAdapter);
                dataExpandableListaAdapter.notifyDataSetChanged();

            }

        }
    }
}
