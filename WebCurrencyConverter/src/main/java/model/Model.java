package model;

import model.currency.RublesToCurrency;

import java.util.ArrayList;

/**
 * Created by BananMan on 21.10.2016.
 */

public class Model {

    private static volatile ArrayList<RublesToCurrency> currencies = new ArrayList<RublesToCurrency>();

    private Model(){
    }

    public static synchronized ArrayList<RublesToCurrency> getModel() {
        return currencies;
    }

    public static synchronized void setModel(ArrayList<RublesToCurrency> currencies) {
        Model.currencies = new ArrayList<RublesToCurrency>(currencies);
    }

}