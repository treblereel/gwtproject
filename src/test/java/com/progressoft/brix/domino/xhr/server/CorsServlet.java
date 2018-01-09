package com.progressoft.brix.domino.xhr.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class CorsServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CorsServlet.class.getCanonicalName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCORSHeaders(req, resp);
        resp.getWriter().print("test content");
    }

    private void setCORSHeaders(HttpServletRequest req, HttpServletResponse resp) {
        String origin = req.getHeader("Origin");
        resp.setHeader("Access-Control-Allow-Origin", origin);
        String queryString = req.getQueryString();
        if (nonNull(queryString) && queryString.contains("credentials")) {
            String[] credentialsParam = queryString.split("=");
            if ("true".equals(credentialsParam[1]))
                resp.addHeader("Access-Control-Allow-Credentials", "true");
        }
        resp.addHeader("Access-Control-Allow-Methods", "GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
