package org.gwtproject.xhr;

import org.gwtproject.xhr.server.CorsServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.LocalConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CorsServer {

    private static final Logger LOGGER = Logger.getLogger(CorsServer.class.getCanonicalName());

    public static void main(String[] args) {
        try {
            Server server = new Server(Integer.parseInt(args[1]));
            Connector con = new LocalConnector(server);
            server.addConnector(con);
            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setResourceBase("/");
            webAppContext.setContextPath("/");
            webAppContext.addServlet(new ServletHolder(new CorsServlet()), "/testCors");
            server.setHandler(webAppContext);
            server.start();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error while starting CORS server");
            System.exit(0);
        }
    }
}
