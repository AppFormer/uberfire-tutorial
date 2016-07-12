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

package org.uberfire.client.screens;

import com.google.gwt.user.client.Event;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Document;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.screens.widgets.ProjectItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.errai.common.client.dom.DOMUtil.removeAllChildren;

@Dependent
@Templated
public class ProjectsView implements ProjectsPresenter.View, IsElement {

    private ProjectsPresenter presenter;

    @Inject
    Document document;

    @Inject
    @DataField( "new-project" )
    Button newProject;

    @Inject
    @DataField( "projects-list" )
    UnorderedList projectsList;

    @Inject
    ManagedInstance<ProjectItem> projects;

    @Override
    public void init( ProjectsPresenter presenter ) {
        this.presenter = presenter;
    }

    @SinkNative( Event.ONCLICK )
    @EventHandler( "new-project" )
    public void addProject( final Event event ) {
        presenter.newProject();
    }

    @Override
    public void clearProjects() {
        removeAllChildren( projectsList );
    }

    @Override
    public void disableProjectCreation() {
        newProject.setDisabled( true );
    }

    @Override
    public void enableProjectCreation() {
        newProject.setDisabled( false );
    }

    @Override
    public void addProject( final String projectName,
                            final boolean active ) {

        ProjectItem projectItem = projects.get();
        projectItem.init( projectName,
                          active,
                          () -> presenter.selectProject( projectName ) );
        projectsList.appendChild( projectItem.getElement() );
    }

}