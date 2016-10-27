package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static java.lang.Thread.sleep;

/**
 * Created by BananMan on 22.10.2016.
 */

@WebListener
public class HTMLParserContextListener implements ServletContextListener{
    Thread updater;
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HTMLParser parser = new HTMLParser();
        parser.updateModel();
        updater = new Thread(new Runnable() {
            public void run() {
                while (true){
                    HTMLParser parser = new HTMLParser();
                    parser.updateModel();
                    try{
                        sleep(300000);		//Приостанавливает поток на 5 минут
                    } catch(InterruptedException e){}
                }
            }
        });
        updater.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        updater.interrupt();
    }
}
