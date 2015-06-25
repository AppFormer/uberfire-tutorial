package com.mycompany.uftutorial.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.BusErrorCallback;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.Header;
import org.uberfire.client.workbench.widgets.menu.WorkbenchMenuBarPresenter;
import org.uberfire.mvp.Command;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.MenuPosition;
import org.uberfire.workbench.model.menu.Menus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class AppMenuBar extends Composite implements Header {

  @Inject
  private WorkbenchMenuBarPresenter menuBarPresenter;

  @Inject
  private PlaceManager placeManager;

  @Inject
  private Caller<AuthenticationService> authService;

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
      .newTopLevelMenu("Perspectives")
        .menus()
          .menu("Home Perspective").respondsWith(makeGoToPlaceCommand(HomePerspective.class)).endMenu()
          .menu("Horizontal Perspective").respondsWith(makeGoToPlaceCommand(HorizontalPerspective.class)).endMenu()
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
      .newTopLevelMenu("Logout")
        .position(MenuPosition.RIGHT)
        .respondsWith(new LogoutCommand())
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

  private class LogoutCommand implements Command {
    @Override
    public void execute() {
      authService.call(new RemoteCallback<Void>() {
        @Override
        public void callback(Void response) {
          redirect(GWT.getHostPageBaseURL() + "login.jsp");
        }
      }, new BusErrorCallback() {
        @Override
        public boolean error(Message message, Throwable throwable) {
          Window.alert("Logout failed: " + throwable);
          return true;
        }
      }).logout();
    }
  }

  private static native void redirect( String url )/*-{
    $wnd.location = url;
  }-*/;
}
