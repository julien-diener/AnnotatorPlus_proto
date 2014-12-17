package org.annotatorplus.server;

import javax.servlet.http.HttpServletRequest;

public class IBCAnnotatorServlet extends AnnotatorServlet {
    private static final long serialVersionUID = 7156067787669333735L;

    @Override
    protected String getAnnotatorBaseURL() {
        return AnnotatorUtils.getBioPortalUri() + "/annotator?" + "apikey=fc4abd1d-ca3e-4e21-96f0-15a16e030889&";
    }

    @Override
    protected String getURLParameters(HttpServletRequest req) {
        String param = super.getURLParameters(req);
        param += AnnotatorUtils.getBioPortalOntologiesURLParameter("IBC");
        return param;
    }

}
