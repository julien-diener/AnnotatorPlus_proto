package org.annotatorplus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AnnotatorServlet extends HttpServlet {
    
    private static final long serialVersionUID = -7313493486599524614L;

    protected String getAnnotatorBaseURL(){
	return "http://data.bioontology.org?";
    }

    // redirect GET to POST
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

	// make query URL
	String url = getAnnotatorBaseURL() + getURLParameters(req);

	// query annotator
	HttpClient client = HttpClientBuilder.create().build(); //TODO remove old: new DefaultHttpClient();
	HttpGet method = new HttpGet(url);

	HttpResponse httpResponse = null;
	try {
	    httpResponse = client.execute(method);
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	// process response
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

	    // output results
	    PrintWriter out = response.getWriter();
	    response.setContentType("application/json;charset=UTF-8");
	    String line = "";
	    while ((line = reader.readLine()) != null) {
		out.println(line);
	    }
	    out.flush();

	} catch (IllegalStateException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    EntityUtils.consume(httpResponse.getEntity());  //TODO remove old: .consumeContent();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
    }

    /**
     * @param {@link HttpServletRequest} request
     * @return String of URL parameters made from given request parameters
     */
    protected String getURLParameters(HttpServletRequest request) {
	Enumeration<String> parameterNames = request.getParameterNames();

	String parameters = "";
	while (parameterNames.hasMoreElements()) {

	    String paramName = parameterNames.nextElement();
	    parameters += paramName + "=";

	    String[] paramValues = request.getParameterValues(paramName);
	    for (int i = 0; i < paramValues.length; i++) {
		String paramValue = paramValues[i];
		if (paramValues.length == 1) {
		    parameters += paramValue + "&";
		} else {
		    if (i == 0) {
			parameters += ""+paramValue;
			if (paramValues.length == 1)
			    parameters += "&";
		    } else if (i == (paramValues.length - 1))
			parameters += "," + paramValue + "&";
		    else
			parameters += "," + paramValue;
		}
	    }
	}
	return parameters;
    } 
}
