package com.mycompany.uftutorial.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.Header;
import org.uberfire.client.workbench.widgets.menu.WorkbenchMenuBarPresenter;
import org.uberfire.mvp.Command;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.Menus;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class AppMenuBar extends Composite implements Header {

  @Inject
  private WorkbenchMenuBarPresenter menuBarPresenter;

  @Inject
  private PlaceManager placeManager;

  @Override
  public Widget asWidget() {
    return menuBarPresenter.getView().asWidget();
  }

  @Override
  public int getOrder() {
    return Integer.MAX_VALUE;
  }

  @Override
  public String getId() {
    return getClass().getName();
  }

  @PostConstruct
  private void initMenus() {
    Menus menus =
      MenuFactory.newTopLevelMenu("Screens")
        .menus()
          .menu("Hello Screen").respondsWith(makeGoToPlaceCommand(HelloWorldScreen.class)).endMenu()
          .menu("Mood Screen").respondsWith(makeGoToPlaceCommand(MoodScreen.class)).endMenu()
        .endMenus()
      .endMenu()
      .newTopLevelMenu("Other")
        .menus()
          .menu("Alert Box").respondsWith(new Command() {
              @Override
              public void execute() {
                Window.alert("Hi. I'm an Alert Box.");
              }
            }).endMenu()
        .endMenus()
      .endMenu()
    .build();

    menuBarPresenter.addMenus(menus);
  }

  private Command makeGoToPlaceCommand(final Class<?> placeClass) {
    return new Command() {
      @Override
      public void execute() {
        placeManager.goTo(placeClass.getName());
      }
    };
  }
}
