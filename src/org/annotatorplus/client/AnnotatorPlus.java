package org.annotatorplus.client;

//import org.annotatorplus.shared.Documentation;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class AnnotatorPlus  implements EntryPoint {

    public void onModuleLoad(){
        //Window.Location.replace(Window.Location.getHost());
        RootPanel.getBodyElement().setInnerHTML(Window.Location.getHost());
        //RootPanel.getBodyElement().setInnerHTML(Documentation.getHTML());
    }
}
