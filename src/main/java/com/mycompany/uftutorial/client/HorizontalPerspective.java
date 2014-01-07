package com.mycompany.uftutorial.client;

import javax.enterprise.context.ApplicationScoped;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.impl.MultiListWorkbenchPanelPresenter;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

@ApplicationScoped
@WorkbenchPerspective(
        identifier = "com.mycompany.uftutorial.client.HorizontalPerspective")
public class HorizontalPerspective {

  @Perspective
  public PerspectiveDefinition getPerspective() {
    final PerspectiveDefinition p = new PerspectiveDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
    p.setName(getClass().getName());

    p.getRoot().addPart(
            new PartDefinitionImpl(
                    new DefaultPlaceRequest(HelloWorldScreen.class.getName())));
    p.getRoot().setElementId("horizontal-rootPanel");

    PanelDefinitionImpl northPanel = new PanelDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
    p.getRoot().insertChild(CompassPosition.NORTH, northPanel);
    northPanel.setElementId("horizontal-northPanel");
    northPanel.setHeight(300);
    northPanel.addPart(
            new PartDefinitionImpl(
                    new DefaultPlaceRequest(MoodScreen.class.getName())));

    return p;
  }

}
