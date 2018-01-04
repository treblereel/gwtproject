package com.progressoft.brix.domino.xhr.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

public class TestServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TestServlet.class.getCanonicalName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            resp.setHeader(headerName, req.getHeader(headerName));
        }
        resp.setStatus(200);
    }
}
