package org.annotatorplus.server;

import javax.servlet.http.HttpServletRequest;

public class SIFRAnnotatorServlet extends IBCAnnotatorServlet {
    private static final long serialVersionUID = 2833332884636729837L;

    @Override
    protected String getURLParameters(HttpServletRequest req) {
        String param = super.getURLParameters(req);
        param += AnnotatorUtils.getBioPortalOntologiesURLParameter("SIFR");
        return param;
    }
}
