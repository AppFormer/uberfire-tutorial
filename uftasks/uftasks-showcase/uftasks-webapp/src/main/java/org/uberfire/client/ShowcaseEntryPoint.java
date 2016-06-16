/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.resources.AppResource;
import org.uberfire.client.views.pfly.menu.MainBrand;
import org.uberfire.client.workbench.events.ApplicationReadyEvent;
import org.uberfire.client.workbench.widgets.menu.WorkbenchMenuBar;
import org.uberfire.shared.authz.UFTasksControllerHelper;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.model.menu.Menus;

import static org.uberfire.workbench.model.menu.MenuFactory.*;

@EntryPoint
public class ShowcaseEntryPoint {

    @Inject
    private WorkbenchMenuBar menubar;

    @Inject
    private PlaceManager placeManager;

    @Inject
    private Event<NotificationEvent> workbenchNotification;

    @PostConstruct
    public void startApp() {
        AppResource.INSTANCE.CSS().ensureInjected();
        hideLoadingPopup();
    }

    private void setupMenu( @Observes final ApplicationReadyEvent event ) {
        final Menus menus =
                newTopLevelMenu( "UF Tasks" )
                        .perspective( "TasksPerspective" )
                        .endMenu()
                        .newTopLevelMenu( "Dashboard" )
                        .perspective( "DashboardPerspective" )
                        .endMenu()
                        .newTopLevelMenu( "About" )
                        .identifier( "uftasks.about" )
                        .respondsWith(() -> Window.alert( "UF tasks showcase" ))
                        .endMenu()
                        .build();

        menubar.addMenus( menus );

        // Check if the welcome notification permission is granted
        if (UFTasksControllerHelper.sayhello()) {
            workbenchNotification.fire(new NotificationEvent("Welcome to the UF tasks showcase", NotificationEvent.NotificationType.SUCCESS));
        }
    }

    @Produces
    @ApplicationScoped
    public MainBrand createBrandLogo() {
        return new MainBrand() {
            @Override
            public Widget asWidget() {
                return new Image( AppResource.INSTANCE.images().ufBrandLogo() );
            }
        };
    }

    //Fade out the "Loading application" pop-up
    private void hideLoadingPopup() {
        final Element e = RootPanel.get( "loading" ).getElement();

        new Animation() {

            @Override
            protected void onUpdate( double progress ) {
                e.getStyle().setOpacity( 1.0 - progress );
            }

            @Override
            protected void onComplete() {
                e.getStyle().setVisibility( Style.Visibility.HIDDEN );
            }
        }.run( 500 );
    }
}