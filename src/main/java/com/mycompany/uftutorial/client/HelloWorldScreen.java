package com.mycompany.uftutorial.client;

import javax.enterprise.context.Dependent;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;

@Dependent
@WorkbenchScreen(identifier = "com.mycompany.uftutorial.client.HelloWorldScreen")
public class HelloWorldScreen {

  private static final String ORIGINAL_TEXT = "Hello UberFire!";

  private Label label = new Label(ORIGINAL_TEXT);

  @WorkbenchPartTitle
  public String getTitle() {
    return "Greetings";
  }

  @WorkbenchPartView
  public IsWidget getView() {
    return label;
  }
}
