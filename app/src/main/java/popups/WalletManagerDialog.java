package popups;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.joaoc.rigsmonitor.R;

import java.util.ArrayList;

import services.WalletManipulatorFile;

/**
 * Created by joaoc on 29/03/2018.
 */

public class WalletManagerDialog {

    private Context _context;
    private WalletManipulatorFile _walletManipulatorFile;
    private ArrayList<String> _wallets_nanopool;
    private ArrayList<String> _wallets_ethermine;
    private ArrayAdapter<String> arrayAdapter;
    private ListView _listView;
    private Button _button_add;
    private Button _button_rm;
    private EditText _editText;
    private RadioGroup _radioGroup;
    private char _c;
    private Dialog _dialog;

    public WalletManagerDialog(Context context){

        this._context = context;
        this._walletManipulatorFile = new WalletManipulatorFile(this._context);
        this._wallets_nanopool = this._walletManipulatorFile.getWalletsNanoPool();
        this._wallets_ethermine = this._walletManipulatorFile.getWalletsEthermine();

    }

    @SuppressLint("ResourceType")
    public void showDiaologWallet(){

        this._dialog = new Dialog(_context);
        this._dialog.setContentView(R.layout.popup_walletmanager);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        createListView();
        this._dialog.show();

    }

    private void createListView(){

        this._listView = (ListView) this._dialog.findViewById(R.id.popup_list_carteias);

        final ArrayList<String> list_wallets = new ArrayList<>();
        list_wallets.addAll(this._wallets_nanopool);
        list_wallets.addAll(this._wallets_ethermine);

        this._editText = (EditText) this._dialog.findViewById(R.id.etxt_wallet);

        this._radioGroup = (RadioGroup) this._dialog.findViewById(R.id.radiogroup_walletmanager);
        Log.d("_listView",(this._listView == null)? "Null" : "Not null");
        Log.d("_radioGroup",(this._radioGroup == null)? "Null" : "Not null");
        //this._radioGroup.check(R.id.radio_nanopool);
        switch (this._radioGroup.getCheckedRadioButtonId()){

            case R.id.radio_nanopool: this._c = 'n'; break;

            case R.id.radio_ethermine: this._c = 'e'; break;

        }

        this._button_add = new Button(this._context);
        this._button_add = (Button) this._dialog.findViewById(R.id.button_add);
        this._button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_editText.getText().toString().length()>10)
                    _walletManipulatorFile.insertWallet(_editText.getText().toString(),_c);
                else
                    Toast.makeText(_dialog.getContext(), "Insira uma carteira válida", Toast.LENGTH_SHORT).show();
                list_wallets.add(_editText.getText().toString());
                _editText.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        this._button_rm = new Button(this._context);
        this._button_rm = this._dialog.findViewById(R.id.button_rm);
        this._button_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_editText.getText().toString().length()>10)
                    _walletManipulatorFile.deleteWallet(_editText.getText().toString(),_c);
                else
                    Toast.makeText(_dialog.getContext(), "Insira uma carteira válida", Toast.LENGTH_SHORT).show();

                ArrayList<String> aux_list = new ArrayList();
                for(int i = 0 ; i < list_wallets.size() ; i++)
                    if(list_wallets.get(i).equals(_editText.getText().toString()))
                        aux_list.add(list_wallets.get(i));

                _editText.setText("");
                list_wallets.clear();
                list_wallets.addAll(aux_list);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        this._listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int item = position;
                String itemValue = (String) _listView.getItemAtPosition(position);
                _editText.setText(itemValue);
            }
        });

        this.arrayAdapter = new ArrayAdapter<String>(this._dialog.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, list_wallets);
        this._listView.setAdapter(arrayAdapter);

    }

}
