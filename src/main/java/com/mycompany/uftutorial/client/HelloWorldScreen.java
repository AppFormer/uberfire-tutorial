package com.mycompany.uftutorial.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.security.client.local.api.SecurityContext;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.mycompany.uftutorial.shared.Mood;

@Dependent
@WorkbenchScreen(identifier = "com.mycompany.uftutorial.client.HelloWorldScreen")
public class HelloWorldScreen {

  @Inject
  private SecurityContext securityContext;

  private final Label label = new Label();

  @PostConstruct
  private void init() {
    label.setText(getInitialLabelText());
  }

  @WorkbenchPartTitle
  public String getTitle() {
    return "Greetings";
  }

  @WorkbenchPartView
  public IsWidget getView() {
    return label;
  }

  private String getInitialLabelText() {
    return "Hello, " + securityContext.getCachedUser().getIdentifier() + ". Welcome to UberFire!";
  }

  public void onMoodChange(@Observes Mood mood) {
    label.setText("I understand you are feeling " + mood.getText());
  }
}
