package services;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by joaoc on 28/10/2017.
 */

public class FileManipulator {

    private final String FILE_NAME = "monitorRIGs.oaf";
    private final String DIR_NAME      = "/appRIGv01/";
    private final String DIR_NANOPOOL = "nanopool.wallets";
    private final String DIR_ETHERMINE = "ethermine.wallets";
    private Context _context;

    public FileManipulator(Context context){

        this._context = context;

    }

    public boolean writeFile(String data, char c){

        String file_name = (c == 'n') ? DIR_NANOPOOL : DIR_ETHERMINE;

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this._context.openFileOutput(file_name, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        return true;
        } catch (FileNotFoundException e) {
            Log.d("ERRO - ","FileManipulator.writeFile::FileNotFoundException");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            Log.d("ERRO - ","FileManipulator.writeFile::IOException");
            e.printStackTrace();
            return false;
        }
    }

    public String readFile(char c){

        String file_name = (c == 'n') ? DIR_NANOPOOL : DIR_ETHERMINE;

        //Recupera o arquivo passando o caminho getFileDir() (Que busca a pasta da aplicação) e FILE_NAME (Nome do arquivo)
        try {
            InputStream inputStream = this._context.openFileInput(file_name);
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }

                bufferedReader.close();
                Log.d("SAIDA - FileManipulator", "readFile() -- data =  "+ stringBuilder.toString());
                return stringBuilder.toString();

            }

        }
        catch (FileNotFoundException e1){
            e1.getMessage();
        }
        catch (IOException e) {
            e.getMessage();
        }

        return "";
    }

}
