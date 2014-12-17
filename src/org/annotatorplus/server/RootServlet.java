package org.annotatorplus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.markdown4j.Markdown4jProcessor;

/**
 * Root page that display simple doc
 * 
 * @author Julien Diener
 */
public class RootServlet extends HttpServlet {

    private static final long serialVersionUID = 8060995079971663594L;

    // redirect GET to POST
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String html;
        URL filename = this.getClass().getResource("/APIdoc.md");
        
       // try reading the API doc file
       try{
            BufferedReader in = new BufferedReader(new InputStreamReader(filename.openStream()));
    
            String inputLine;
            String md = "";
            while ((inputLine = in.readLine()) != null)
                md += inputLine+"\n";
            in.close();
            
            html = markdown2html(md);
            
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            
            html  = "<p>API doc file:"+filename+"</p>\n\n";
            html += sw.toString(); // stack trace as a string
        }
    
        // output results
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        out.println(html.replace("\n","<br>"));
        out.flush();
    }

    public static String markdown2html(String md){
        try{
            return new Markdown4jProcessor().process(md);
        }catch(IOException e){
            return "<p><strong>Problem processing markup documentation:</strong></p>" + e;
        }
    }
}
