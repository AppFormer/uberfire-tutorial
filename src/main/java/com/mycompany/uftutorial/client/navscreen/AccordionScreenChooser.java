package com.mycompany.uftutorial.client.navscreen;

import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.util.Layouts;
import org.uberfire.lifecycle.OnOpen;

import com.google.gwt.user.client.ui.Composite;

/**
 * A chooser that presents screens grouped by category. Clicking a screen causes a PlaceManager.goTo() for that screen's
 * place.
 */
@Templated
@WorkbenchScreen(identifier="AccordionScreenChooser")
public class AccordionScreenChooser extends Composite {

  @Override
  @WorkbenchPartTitle
  public String getTitle() {
    return "Screen chooser";
  }

  @OnOpen
  public void onOpen() {
    Layouts.disableNearestScrollPanel(this);
  }
}
