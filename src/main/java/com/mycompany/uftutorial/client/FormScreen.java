package com.mycompany.uftutorial.client;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Model;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchScreen;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.mycompany.uftutorial.shared.PatternFlyExampleFormData;

/**
 * Demonstrates how to use Errai UI and Errai Data Binding within an UberFire screen. The HTML for this screen was
 * copied verbatim from the PatternFly example at
 * https://www.patternfly.org/wp-content/uploads/patternfly/tests/form.html.
 */
@Templated
@WorkbenchScreen(identifier="FormScreen")
public class FormScreen extends Composite {

  @Inject @Model
  private PatternFlyExampleFormData model;

  @Inject @Bound @DataField
  private TextBox textInput;

  @Inject @Bound @DataField
  private TextBox textInput2;

  @Inject @Bound @DataField
  private TextBox textInput3;

  @Inject @Bound @DataField
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
    Window.alert("You entered:" + model);
  }
}
