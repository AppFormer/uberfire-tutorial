package com.mycompany.uftutorial.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import org.gwtbootstrap3.client.ui.NavbarBrand;
import org.gwtbootstrap3.client.ui.html.Text;
import org.uberfire.client.views.pfly.menu.MainBrand;

import com.google.gwt.dom.client.Style.Unit;

@Dependent @MainBrand
public class MainBrandLink extends NavbarBrand {

  @PostConstruct
  private void setup() {
    setHref("http://uberfireframework.org");
    add(new Text("UberFire + PatternFly"));

    // these styles satisfy the requirement that the widget takes exactly 25px of vertical space
    getElement().getStyle().setHeight(25, Unit.PX);
    getElement().getStyle().setFontSize(12, Unit.PX);
    getElement().getStyle().setPaddingTop(2, Unit.PX);
  }
}
