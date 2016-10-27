package servlets;

import dao.DAO;
import model.currency.RublesToCurrency;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by BananMan on 22.10.2016.
 */
public class MainPage extends HttpServlet {

    private static BigDecimal rublesQuantity = new BigDecimal("100");
    private static BigDecimal toConvert = new BigDecimal("100");
    private static String currencyFrom = "USD";
    private static String currencyWhere = "EUR";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //rublesQuantity = new BigDecimal(req.getParameter("rubles").replace(',', '.'));
        //printHtml(resp);


        toConvert = new BigDecimal(req.getParameter("quantity"));
        if(toConvert.compareTo(new BigDecimal("0")) == -1) {
            toConvert = toConvert.abs();
        }
        currencyFrom = req.getParameter("from");
        currencyWhere = req.getParameter("where");

        printHtml2(resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //printHtml(resp);
        printHtml2(resp);
    }

    /**
     * method for another task presentation
     */
    private void printHtml(HttpServletResponse resp) throws ServletException, IOException{
        resp.setCharacterEncoding("Cp1251");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Конвертер валют</h1>");
        out.println("</header>");
        out.println("<form value=\"100\" method=\"post\">");
        out.println("Рубли для перевода: <input type=\"text\" name=\"rubles\"><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.print("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"4\" border=\"1\">\n" +
                "       <colgroup>\n" +
                "        <col align=\"center\" />\n" +
                "        <col align=\"left\" />\n" +
                "        <col align=\"center\" />\n" +
                "        <col align=\"center\" />\n" +
                "        <col align=\"center\" />\n" +
                "       </colgroup>\n" +
                "       <caption>\n" +
                "       Перевод рублей в другие валюты\n" +
                "       </caption>\n" +
                "       <thead>\n" +
                "        <tr>\n" +
                "         <th>Код</th>\n" +
                "         <th>Валюта</th>\n" +
                "         <th>Кол-во в рублях</th>\n" +
                "         <th>Перевод в другую валюту</th>\n" +
                "         <th>Изменение единицы валюты в рублях</th>\n" +
                "        </tr>\n" +
                "       </thead>\n" +
                "       <tbody>\n");
        for(RublesToCurrency element : DAO.getModel()) {
            out.println("        <tr>");
            out.println("         <td>" + element.getAbbreviation() + "</td>");
            out.println("         <td>" + element.getName() + "</td>");
            out.println("         <td>" + rublesQuantity + "</td>");
            out.println("         <td>" + rublesQuantity.multiply(new BigDecimal(element.getQuantity()
                    /element.getRate() + "")).setScale(4, RoundingMode.UP) + "</td>");
            if(element.getChanges() >= 0) {
                out.println("         <td>+" + element.getChanges() + "</td>");
            }
            else {
                out.println("         <td>" + element.getChanges() + "</td>");
            }
            out.println("        </tr>");
        }
        out.print(
                "       </tbody>\n" +
                        "      </table> ");
        out.println("</body>");
        out.println("</html>");
    }
    private void printHtml2(HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Cp1251");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Конвертер валют</h1>");
        out.println("</header>");

        out.println("<FORM name=\"formFrom\" method=\"post\">");
        out.println("Валюта, из которой осуществляется перевод:");
        out.println("<SELECT NAME=\"from\" SIZE=" + 1 + " >");
        for(RublesToCurrency element : DAO.getModel()) {
            out.println("<OPTION value=\"" + element.getAbbreviation() + "\"> " + element.getName());
            //System.out.println("Set abbreviation:" + element.getAbbreviation());
        }
        out.println("</SELECT>");
        out.println("<br><br><br><br>");
        out.println("Валюта, в которую осуществляется перевод:");
        out.println("<SELECT NAME=\"where\" SIZE=" + 1 + " >");
        for(RublesToCurrency element : DAO.getModel()) {
            out.println("<OPTION value=\"" + element.getAbbreviation() + "\"> " + element.getName());
        }
        out.println("</SELECT>");
        out.println("<br><br>");
        out.println("<br>Валютные единицы, к переводу: <input type=\"number\" name=\"quantity\"> <input type=\"submit\" value=\"Submit\"> <br><br><br><br><br>");
        out.println("</FORM>");
        RublesToCurrency from = DAO.get(DAO.getIndexByAbbreviation(currencyFrom));
        RublesToCurrency where = DAO.get(DAO.getIndexByAbbreviation(currencyWhere));


        out.print("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"4\" border=\"1\">\n" +
                "       <colgroup>\n" +
                "        <col align=\"center\" />\n" +
                "        <col align=\"center\" />\n" +
                "       </colgroup>\n" +
                "       <caption>\n" +
                "       Перевод " + from.getName()
                + " в " + where.getName() + "\n" +
                "       </caption>\n" +
                "       <thead>\n" +
                "        <tr>\n" +
                "         <th>" + from.getName() + "</th>\n" +
                "         <th>" + where.getName() + "</th>\n" +
                "        </tr>\n" +
                "       </thead>\n" +
                "       <tbody>\n");
        out.println("        <tr>");
        out.println("         <td>" + toConvert + "</td>");

        //calculate second currency amount to display
        BigDecimal rubles = toConvert.multiply(new BigDecimal(from.getRate()/from.getQuantity() + ""));
        out.println("         <td>" + rubles.multiply(new BigDecimal(where.getQuantity()/where.getRate() + ""))
                .setScale(4, RoundingMode.UP) + "</td>");
        out.println("        </tr>");
        out.println("       </tbody>");
        out.println("      </table> ");
        out.println("</body>");
        out.println("</html>");
    }
}
