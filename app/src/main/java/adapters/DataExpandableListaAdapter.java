package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.joaoc.rigsmonitor.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

//import java.util.HashMap;
import java.util.List;

import pojo.Data;
import pojo.Worker;

/**
 * Created by joaoc on 04/03/2018.
 */

public class DataExpandableListaAdapter extends BaseExpandableListAdapter{

    private Context _context;
    private List<Data> _datasHeader;
    //private HashMap<String, List<Worker>> _workersChild;

    public DataExpandableListaAdapter(Context context, List<Data> datasHeader/*, HashMap<String, List<Worker>> workersChild*/){

        this._context = context;
        this._datasHeader = datasHeader;
        //this._workersChild = workersChild;

    }

    @Override
    public int getGroupCount() {
        return _datasHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._datasHeader.get(groupPosition).workers.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._datasHeader.get(groupPosition);
    }

    @Override
    public Worker getChild(int groupPosition, int childPosition) {
        return this._datasHeader.get(groupPosition).workers.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //Chama o objeto a ser printado
        Data data = (Data) getGroup(groupPosition);

        //Invoca a layout
        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header_data, null);
        }

        //Invocando os componentes visuais
        TextView accountValue = (TextView) convertView.findViewById(R.id.accountValue);

        TextView hashRateValue = (TextView) convertView.findViewById(R.id.hashrateValue);

        TextView balanceValue = (TextView) convertView.findViewById(R.id.balanceValue);

        //Atribuindo valor aos componentes visuais
        accountValue.setText(data.account);

        hashRateValue.setText(data.hashrate);

        balanceValue.setText(data.balance);

        //Cria o Objeto Grafico e o invoca
        GraphView graphView = (GraphView) convertView.findViewById(R.id.graph);

        //Cria o obejeto resposavel por inserir valores no grafico
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, Double.parseDouble(data.avghashrate.get(0))),
                new DataPoint(1, Double.parseDouble(data.avghashrate.get(1))),
                new DataPoint(2, Double.parseDouble(data.avghashrate.get(2))),
                new DataPoint(3, Double.parseDouble(data.avghashrate.get(3))),
                new DataPoint(4, Double.parseDouble(data.avghashrate.get(4)))
        });
        //Adiciona os valores no grafico
        graphView.addSeries(series);

        //Chama um objeto para criar labels customizadas (setHorizontalLabels e setVerticalLabels)
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"24h", "12h", "6h", "3h", "1h"});

        //Insere as novas labels no grafico
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Worker worker = (Worker) getChild(groupPosition,childPosition);

        //if(convertView == null){

            LayoutInflater layoutInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_child_worker,null);

        //}

        TextView accountValue = (TextView) convertView.findViewById(R.id.idValue);

        TextView hashRateValue = (TextView) convertView.findViewById(R.id.hashrate);

        //Atribuindo valor aos componentes visuais
        accountValue.setText((String) worker.get_worker());

        hashRateValue.setText(worker.get_avghashrate());

        //Cria o Objeto Grafico e o invoca
        GraphView graphView = (GraphView) convertView.findViewById(R.id.graph);

        //Cria o obejeto resposavel por inserir valores no grafico
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, Double.parseDouble(worker.get_hashRates().get(4))),
                new DataPoint(1, Double.parseDouble(worker.get_hashRates().get(3))),
                new DataPoint(2, Double.parseDouble(worker.get_hashRates().get(2))),
                new DataPoint(3, Double.parseDouble(worker.get_hashRates().get(1))),
                new DataPoint(4, Double.parseDouble(worker.get_hashRates().get(0)))
        });
        //Adiciona os valores no grafico
        graphView.addSeries(series);

        //Chama um objeto para criar labels customizadas (setHorizontalLabels e setVerticalLabels)
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"24h", "12h", "6h", "3h", "1h"});

        //Insere as novas labels no grafico
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
