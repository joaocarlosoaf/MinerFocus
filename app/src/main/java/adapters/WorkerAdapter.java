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

import pojo.Worker;

/**
 * Created by joaoc on 01/11/2017.
 */

public class WorkerAdapter extends BaseAdapter {

    private Context context;
    private List<Worker> workers;

    public WorkerAdapter(Context context, List<Worker> workers){

        this.context = context;
        this.workers = workers;

    }

    @Override
    public int getCount() {
        return this.workers.size();
    }

    @Override
    public Object getItem(int position) {
        return this.workers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
/*
        //Chama o objeto a ser printado
        Worker worker = workers.get(position);

        //Invoca a layout
        View line = LayoutInflater.from(context).inflate(R.layout.item_workers_layout, null);

        //Atribui os valores

        //Invocando os componentes visuais
        TextView accountValue = (TextView) line.findViewById(R.id.accountValue);

        TextView hashRate = (TextView) line.findViewById(R.id.hashrate);
        TextView hashRateValue = (TextView) line.findViewById(R.id.hashrateValue);

        //Atribuindo valor aos componentes visuais
        accountValue.setText(worker.id);

        hashRate.setText(worker.hashrate);
        hashRateValue.setText(worker.hashrate);

        //Cria o Objeto Grafico e o invoca
        GraphView graphView = (GraphView) line.findViewById(R.id.graph);

        //Cria o obejeto resposavel por inserir valores no grafico
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, Double.parseDouble(worker.avghashrate.get(0))),
                new DataPoint(1, Double.parseDouble(worker.avghashrate.get(1))),
                new DataPoint(2, Double.parseDouble(worker.avghashrate.get(2))),
                new DataPoint(3, Double.parseDouble(worker.avghashrate.get(3))),
                new DataPoint(4, Double.parseDouble(worker.avghashrate.get(4)))
        });
        //Adiciona os valores no grafico
        graphView.addSeries(series);

        //Chama um objeto para criar labels customizadas (setHorizontalLabels e setVerticalLabels)
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"24h", "12h", "6h", "3h", "1h"});

        //Insere as novas labels no grafico
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        return line;
        */
        return null;
    }
}
