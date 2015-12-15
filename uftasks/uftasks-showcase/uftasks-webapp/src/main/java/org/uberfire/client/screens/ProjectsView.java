/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.uberfire.client.screens;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.LinkedGroup;
import org.gwtbootstrap3.client.ui.LinkedGroupItem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

@Dependent
public class ProjectsView extends Composite implements ProjectsPresenter.View {

    interface Binder
            extends
            UiBinder<Widget, ProjectsView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    private ProjectsPresenter presenter;

    @UiField
    Button newProject;

    @UiField
    LinkedGroup projects;

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );
        newProject.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.newProject();
            }
        } );
    }

    @Override
    public void init( ProjectsPresenter presenter ) {
        this.presenter = presenter;
    }

    @Override
    public void clearProjects() {
        projects.clear();
    }

    @Override
    public void addProject( final String projectName,
                            final boolean active ) {
        final LinkedGroupItem projectItem = createProjectItems( projectName, active );
        projects.add( projectItem );
    }

    private LinkedGroupItem createProjectItems( final String projectName,
                                                boolean active ) {
        final LinkedGroupItem projectItem = GWT.create( LinkedGroupItem.class );
        projectItem.setText( projectName );
        projectItem.setActive( active );
        projectItem.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.selectProject( projectName );
            }
        } );
        return projectItem;
    }

}