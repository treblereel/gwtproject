/*
 * Copyright © 2019 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.xhr.server;

import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
