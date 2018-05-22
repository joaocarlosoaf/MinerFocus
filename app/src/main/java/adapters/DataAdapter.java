package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.joaoc.rigsmonitor.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import pojo.Data;

/**
 * Created by joaoc on 04/12/2017.
 */

public class DataAdapter extends BaseAdapter {

    private Context context;
    private List<Data> datas;

    public DataAdapter(Context context, List<Data> datas){

        this.context = context;
        this.datas = datas;

    }

    @Override
    public int getCount() {
        return this.datas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Chama o objeto a ser printado
        Data data = datas.get(position);

        //Invoca a layout
        View line = LayoutInflater.from(context).inflate(R.layout.list_group_header_data, null);

        //Atribui os valores

        //Invocando os componentes visuais
        TextView accountValue = (TextView) line.findViewById(R.id.accountValue);

        TextView hashRateValue = (TextView) line.findViewById(R.id.hashrateValue);

        TextView balanceValue = (TextView) line.findViewById(R.id.balanceValue);

        //Atribuindo valor aos componentes visuais
        accountValue.setText(data.account);

        hashRateValue.setText(data.hashrate);

        balanceValue.setText(data.balance);

        //Cria o Objeto Grafico e o invoca
        GraphView graphView = (GraphView) line.findViewById(R.id.graph);

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

        return line;
    }
}
