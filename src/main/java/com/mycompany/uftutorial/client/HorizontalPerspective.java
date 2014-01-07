package com.mycompany.uftutorial.client;

import javax.enterprise.context.ApplicationScoped;

import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PanelType;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.Position;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

@ApplicationScoped
@WorkbenchPerspective(
    identifier = "com.mycompany.uftutorial.client.HorizontalPerspective")
public class HorizontalPerspective {

  @Perspective
  public PerspectiveDefinition getPerspective() {
    final PerspectiveDefinition p = new PerspectiveDefinitionImpl(PanelType.ROOT_LIST);
    p.setTransient(true);
    p.setName(getClass().getName());

    p.getRoot().addPart(
            new PartDefinitionImpl(
                    new DefaultPlaceRequest(HelloWorldScreen.class.getName())));

    PanelDefinitionImpl northPanel = new PanelDefinitionImpl(PanelType.MULTI_LIST);
    p.getRoot().insertChild(Position.NORTH, northPanel);
    northPanel.setHeight(300);
    northPanel.addPart(
            new PartDefinitionImpl(
                    new DefaultPlaceRequest(MoodScreen.class.getName())));

    return p;
  }

}
