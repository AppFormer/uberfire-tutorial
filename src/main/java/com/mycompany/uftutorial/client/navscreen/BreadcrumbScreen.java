package com.mycompany.uftutorial.client.navscreen;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Breadcrumbs;
import org.gwtbootstrap3.client.ui.ListItem;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.events.PerspectiveChange;
import org.uberfire.client.workbench.events.SelectPlaceEvent;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.PerspectiveDefinition;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
@WorkbenchScreen(identifier = "BreadcrumbScreen")
public class BreadcrumbScreen {

  @Inject
  private PlaceManager placeManager;

  private PerspectiveDefinition currentPerspective;
  private PlaceRequest currentPlace;

  private final Breadcrumbs view = new Breadcrumbs();

  @WorkbenchPartView
  public Widget getView() {
    return view;
  }

  @WorkbenchPartTitle
  public String getTitle() {
    return "Breadcrumbs";
  }

  private void refreshView() {
    view.clear();
    if (currentPerspective != null) {
      AnchorListItem perspectiveBreadcrumb = new AnchorListItem(currentPerspective.getName());
      perspectiveBreadcrumb.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          placeManager.goTo(currentPerspective.getName());
        }
      });
      view.add(perspectiveBreadcrumb);
    }
    if (currentPlace != null) {
      view.add(new ListItem(currentPlace.getIdentifier()));
    }
  }

  void onPerspectiveChange(@Observes PerspectiveChange pc) {
    currentPerspective = pc.getPerspectiveDefinition();
    refreshView();
  }

  void onChangePlace(@Observes SelectPlaceEvent spe) {
    currentPlace = spe.getPlace();
    refreshView();
  }

}
