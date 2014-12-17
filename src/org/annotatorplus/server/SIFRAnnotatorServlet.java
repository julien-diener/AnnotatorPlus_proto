package org.annotatorplus.server;

import javax.servlet.http.HttpServletRequest;


public class SIFRAnnotatorServlet extends AnnotatorServlet{
    private static final long serialVersionUID = 2833332884636729837L;
    
    @Override
    protected String getAnnotatorBaseURL(){
	return globalAnnotatorUtils.getBioPortalUri() + "/annotator?";
    }
    
    @Override
    protected String getURLParameters(HttpServletRequest req) {
	String param = super.getURLParameters(req);
	param += globalAnnotatorUtils.getBioPortalOntologiesURLParameter("SIFR");
	return param;
    }
}
