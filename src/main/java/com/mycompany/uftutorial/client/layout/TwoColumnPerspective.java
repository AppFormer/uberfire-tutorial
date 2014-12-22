package com.mycompany.uftutorial.client.layout;

import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPanel;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.widgets.listbar.ResizeFlowPanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;

@Templated
@WorkbenchPerspective(identifier="TwoColumnPerspective", isDefault=true)
public class TwoColumnPerspective extends Composite implements RequiresResize, ProvidesResize {

  @Inject
  @DataField
  @WorkbenchPanel(parts="AccordionScreenChooser")
  ResizeFlowPanel leftHandColumn;

  @Inject
  @DataField
  @WorkbenchPanel(parts="BreadcrumbScreen")
  ResizeFlowPanel breadcrumbPanel;

  @Inject
  @DataField
  @WorkbenchPanel(parts="FormScreen", isDefault=true)
  ResizeFlowPanel contentArea;

  @Override
  public void onResize() {
    breadcrumbPanel.getElement().getStyle().setHeight(40, Unit.PX);
    contentArea.getElement().getStyle().setHeight(getOffsetHeight() - 40, Unit.PX);

    leftHandColumn.onResize();
    breadcrumbPanel.onResize();
    contentArea.onResize();
  }
}
