package dao;

import model.Model;
import model.currency.RublesToCurrency;

import java.util.ArrayList;

/**
 * Created by BananMan on 21.10.2016.
 */
public class DAO {

    private DAO() {}

    public static void update(ArrayList<RublesToCurrency> model){
        Model.setModel(model);
    }

    public static void setModel(ArrayList<RublesToCurrency> model){
        Model.setModel(model);
    }

    public static void add(RublesToCurrency newObject){
        Model.getModel().add(newObject);
    }
    public static ArrayList<RublesToCurrency> getModel(){
        return Model.getModel();
    }


    public static RublesToCurrency get(int index){
        return Model.getModel().get(index);
    }

    public static int getIndexByName(String name){
        for (int index = 0; index < Model.getModel().size(); index++) {
            if(Model.getModel().get(index).getName().equals(name)){
                return index;
            }
        }
        return -1;
    }

    public static int getIndexByAbbreviation(String abbreviation){
        for (int index = 0; index < Model.getModel().size(); index++) {
            if(Model.getModel().get(index).getAbbreviation().equals(abbreviation)){
                return index;
            }
        }
        return -1;
    }

    public static ArrayList<RublesToCurrency> getAllByQuantity(int quantity){
        ArrayList<RublesToCurrency> answer = new ArrayList<RublesToCurrency>();
        for (RublesToCurrency currency : Model.getModel()) {
            if(currency.getQuantity() == quantity){
                answer.add(new RublesToCurrency());
            }
        }
        return answer;
    }


}
