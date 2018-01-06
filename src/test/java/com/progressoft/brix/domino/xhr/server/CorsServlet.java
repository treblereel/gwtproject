package com.progressoft.brix.domino.xhr.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCORSHeaders(req, resp);
        resp.getWriter().print("test content");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCORSHeaders(req, resp);
    }

    private void setCORSHeaders(HttpServletRequest req, HttpServletResponse resp) {
        String origin = req.getHeader("Origin");
        resp.setHeader("Access-Control-Allow-Origin", origin);

        if ("true".equals(req.getHeader("server-credentials")))
            resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setHeader("Access-Control-Allow-Headers", "server-credentials");
    }
}
