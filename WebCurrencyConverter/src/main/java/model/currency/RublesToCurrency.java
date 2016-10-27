package model.currency;

/**
 * Created by BananMan on 20.10.2016.
 */

/*Class uses to save value in rubles relating to another currency*/

public class RublesToCurrency {

    String abbreviation;

    String name;

    int quantity;

    double rate;

    double changes;


    public RublesToCurrency(){}

    public RublesToCurrency(String abbreviation, String name, int quantity, double rate, double changes){
        this.abbreviation = abbreviation;
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.changes = changes;
    }



    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getChanges() {
        return changes;
    }

    public void setChanges(double changes) {
        this.changes = changes;
    }
}
