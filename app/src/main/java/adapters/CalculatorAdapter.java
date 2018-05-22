package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.joaoc.rigsmonitor.R;

import java.util.List;

import pojo.CalculatorDataWorker;

/**
 * Created by joaoc on 02/01/2018.
 */

public class CalculatorAdapter extends BaseAdapter{

    private Context context;
    private List<CalculatorDataWorker> calculatorDataWorkers;

    public CalculatorAdapter(Context context, List<CalculatorDataWorker> calculatorDataWorkers){

        this.context = context;
        this.calculatorDataWorkers = calculatorDataWorkers;

    }

    @Override
    public int getCount() {
        return this.calculatorDataWorkers.size();
    }

    @Override
    public Object getItem(int position) {
        return calculatorDataWorkers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CalculatorDataWorker calculatorDataWorker = this.calculatorDataWorkers.get(position);

        // LayoutInflater.from(context).inflate(R.layout.item_workers_layout, null)
        View line = LayoutInflater.from(context).inflate(R.layout.item_calculadora_layout,null);

        TextView accountValue = (TextView) line.findViewById(R.id.txtValueAccount);
        accountValue.setText((CharSequence) calculatorDataWorker.getAccountValue());

        TextView hashrateValue = (TextView) line.findViewById(R.id.txtHashrate);
        hashrateValue.setText(calculatorDataWorker.getHashrateValue());

        TextView btcForHourValue = (TextView) line.findViewById(R.id.txtBtcHourValue);
        btcForHourValue.setText(calculatorDataWorker.getBtcForHourValue());

        TextView usdForHourValue = (TextView) line.findViewById(R.id.txtUsdHourValue);
        usdForHourValue.setText(calculatorDataWorker.getUsdForHourValue());

        TextView btcForDayValue = (TextView) line.findViewById(R.id.txtBtcDayValue);
        btcForDayValue.setText(calculatorDataWorker.getBtcForDayValue());

        TextView usdForDayValue = (TextView) line.findViewById(R.id.txtUsdDayValue);
        usdForDayValue.setText(calculatorDataWorker.getUsdForDayValue());

        TextView btcForWeekValue = (TextView) line.findViewById(R.id.txtBtcWeekValue);
        btcForWeekValue.setText(calculatorDataWorker.getBtcForWeekValue());

        TextView usdForWeekValue = (TextView) line.findViewById(R.id.txtUsdWeekValue);
        usdForWeekValue.setText(calculatorDataWorker.getUsdForWeekValue());

        TextView btcForMonthValue = (TextView) line.findViewById(R.id.txtBtcMonthValue);
        btcForMonthValue.setText(calculatorDataWorker.getBtcForMonthValue());

        TextView usdForMonthValue = (TextView) line.findViewById(R.id.txtUsdMonthValue);
        usdForMonthValue.setText(calculatorDataWorker.getUsdForMonthValue());

        return line;
    }
}
