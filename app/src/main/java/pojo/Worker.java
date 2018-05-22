package pojo;

import java.util.ArrayList;

/**
 * Created by joaoc on 10/11/2017.
 * Classe worker é reponsável por armazenar informações de cada RIG
 */

public class Worker {

    public String _worker;
    public String _avghashrate;
    public ArrayList<String> _hashRates;
    //private String h1, h3, h6, h12, h24;


    public String get_worker() {
        return this._worker;
    }

    public String get_avghashrate() {
        return this._avghashrate;
    }

    public ArrayList<String> get_hashRates() {
        return this._hashRates;
    }

    public Worker(String worker, String avghashrate, ArrayList<String> hashRates){
                    //String h1, String h3, String h6, String h12, String h24){

        this._worker = worker;
        this._avghashrate = avghashrate;
        this._hashRates = hashRates;
        /*this.h1 = h1;
        this.h3 = h3;
        this.h6 = h6;
        this.h12 = h12;
        this.h24 = h24;*/

    }


}
