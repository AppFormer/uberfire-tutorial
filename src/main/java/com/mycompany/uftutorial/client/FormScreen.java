package com.mycompany.uftutorial.client;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchScreen;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

@Templated
@WorkbenchScreen(identifier="FormScreen")
public class FormScreen extends Composite {

  @Inject @DataField
  private TextBox textInput;

  @Inject @DataField
  private TextBox textInput2;

  @Inject @DataField
  private TextBox textInput3;

  @Inject @DataField
  private TextBox textInput4;

  @Inject @DataField
  private Button saveButton;

  @Inject @DataField
  private Button cancelButton;

  @Override
  @WorkbenchPartTitle
  public String getTitle() {
    return "Example Form";
  }

  @EventHandler("saveButton")
  public void onSave(ClickEvent e) {
    Window.alert("You entered:"
            + "\n1: " + textInput.getText()
            + "\n2: " + textInput2.getText()
            + "\n3: " + textInput3.getText()
            + "\n4: " + textInput4.getText());
  }
}
