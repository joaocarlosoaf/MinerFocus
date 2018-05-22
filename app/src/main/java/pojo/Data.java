package pojo;

import java.util.ArrayList;

/**
 * Created by joaoc on 04/12/2017.
 * Classe worker é reponsável por armazenar informações da mineração
 */

public class Data {

    public String account;
    public String balance;
    public String hashrate;
    public ArrayList<String> avghashrate;
    public ArrayList<Worker> workers;

    public Data(String account, String balance, String hashrate,
                ArrayList<String> avghashrate,
                ArrayList<Worker> workers){

        this.account = account;
        this.balance = balance;
        this.hashrate = hashrate;
        this.avghashrate = avghashrate;
        this.workers = workers;

    }


}
