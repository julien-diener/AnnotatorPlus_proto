package org.annotatorplus.server;

import javax.servlet.http.HttpServletRequest;


public class IBCAnnotatorServlet extends AnnotatorServlet{
    private static final long serialVersionUID = 7156067787669333735L;
    
    @Override
    protected String getAnnotatorBaseURL(){
	return globalAnnotatorUtils.getBioPortalUri() + "/annotator?";
    }
    
    @Override
    protected String getURLParameters(HttpServletRequest req) {
	String param = super.getURLParameters(req);
	param += globalAnnotatorUtils.getBioPortalOntologiesURLParameter("IBC");
	return param;
    }

}
