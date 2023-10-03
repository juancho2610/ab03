package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;



/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String webappDirLocation = "src/main/webapp/";
        
        String webPort = System.getenv("PORT");
        if( webPort == null || webPort.isEmpty()){
            webPort= "8080";
        }
     
        Server sever = new Server(Integer.valueOf(webPort));
        WebAppContext root= new WebAppContext();
        
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        
        PersistenceManager.getIntstance().getEntityManagerFactory();
        
        root.setParentLoaderPriority(true);
        
        sever.setHandler(root);
        sever.start();
        sever.join();
    }

}
