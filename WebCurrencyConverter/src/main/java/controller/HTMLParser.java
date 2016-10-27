package controller;

/**
 * Created by BananMan on 20.10.2016.
 */

import dao.DAO;
import model.currency.RublesToCurrency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HTMLParser {

    public void updateModel() {
        DAO.update(parseHTML());
    }

    public ArrayList<RublesToCurrency> parseHTML() {
        // Parse HTML String using JSoup library
        // JSoup Reading HTML page from URL
        Document doc = null;
        ArrayList<RublesToCurrency> currencyList = new ArrayList<RublesToCurrency>();


        // make it coz we don't have a rubles in our table
        currencyList.add(new RublesToCurrency("RUB", "Рубли", 1, 1, 0));
        try {
            doc = Jsoup.connect("http://www.finmarket.ru/currency/rates/").get();

            Elements elements = doc.getElementsByClass("karramba").get(0)
                    .getElementsByTag("tbody").get(0).getElementsByTag("tr");
            for (Element element : elements) {
                Elements currencyFilds = element.getElementsByTag("td");

                String abbreviation = currencyFilds.get(0).ownText();
                String name = currencyFilds.get(1).getElementsByTag("a").get(0).ownText();
                Integer quantity = Integer.parseInt(currencyFilds.get(2).ownText().replace("\u00a0", ""));
                Double rate = Double.parseDouble(currencyFilds.get(3).ownText().replace(',', '.'));
                Double changes = Double.parseDouble(currencyFilds.get(4).ownText().replace(',', '.'));

                currencyList.add(new RublesToCurrency(abbreviation, name, quantity, rate, changes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return currencyList;
    }
}
