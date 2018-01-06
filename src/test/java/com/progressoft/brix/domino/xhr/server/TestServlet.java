package com.progressoft.brix.domino.xhr.server;

import javax.servlet.ServletException;
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
        if ("true".equals(req.getParameter("cors"))) {
            LOGGER.info("cors");
            String origin = req.getHeader("Origin");
//            resp.addHeader("Access-Control-Allow-Origin", isNull(origin) ? "*" : origin);
//
//            if ("true".equals(req.getParameter("credentials")))
//                resp.addHeader("Access-Control-Allow-Credentials", "true");

//            resp.addHeader("Access-Control-Request-Methods", req.getHeader("*"));
            resp.getWriter().print("test content");
        } else {
            LOGGER.info(req.getParameterMap().toString());
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                resp.setHeader(headerName, req.getHeader(headerName));
            }
            resp.setStatus(200);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("options");
    }
}
