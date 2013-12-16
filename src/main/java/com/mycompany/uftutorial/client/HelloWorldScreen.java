package com.mycompany.uftutorial.client;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.mycompany.uftutorial.shared.Mood;

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

  public void onMoodChange(@Observes Mood mood) {
    label.setText("I understand you are feeling " + mood.getText());
  }
}