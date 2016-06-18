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

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.client.screens.popup.NewProjectPresenter;
import org.uberfire.security.annotations.ResourceCheck;
import org.uberfire.shared.events.ProjectSelectedEvent;
import org.uberfire.shared.model.Project;

import static org.uberfire.shared.authz.ProjectConstants.*;
import static org.uberfire.shared.authz.UFTasksControllerHelper.*;

@ApplicationScoped
@WorkbenchScreen(identifier = "ProjectsPresenter")
public class ProjectsPresenter {

    public interface View extends UberView<ProjectsPresenter> {

        void clearProjects();

        void enableProjectCreation(boolean enabled );

        void addProject( String projectName,
                         boolean selected );
    }

    @Inject
    private View view;

    @Inject
    private NewProjectPresenter newProjectPresenter;

    @Inject
    private Event<ProjectSelectedEvent> projectSelectedEvent;

    private List<Project> projects = new ArrayList<Project>();

    @WorkbenchPartTitle
    public String getTitle() {
        return "Projects";
    }

    @WorkbenchPartView
    public UberView<ProjectsPresenter> getView() {
        return view;
    }

    @PostConstruct
    public void init() {
        view.enableProjectCreation( false );

        // The Project security API can be used to check project creation permission
        projects().create().granted( () -> {
            view.enableProjectCreation( true );
        } );
    }

    // Creation of projects is restricted
    @ResourceCheck(type=PROJECT, action=CREATE, onGranted="onCreateGranted", onDenied="onCreateDenied")
    public void newProject() {
        newProjectPresenter.show( this );
    }

    public void onCreateGranted() {
        //Project creation allowed
    }

    public void onCreateDenied() {
       //Project creation NOT allowed
    }

    // Creation of projects is restricted
    @ResourceCheck(type=PROJECT, action=CREATE)
    public void createNewProject( String projectName ) {
        projects.add( new Project( projectName ) );
        this.updateView();
    }

    protected void updateView() {
        view.clearProjects();
        for ( Project project : projects ) {

            // The Project is displayed only if read permission is granted on it
            project(project).read().granted( () -> {
                view.addProject( project.getName(), project.isSelected() );
            } );
        }
    }

    public void selectProject( String projectName ) {
        setActiveProject( projectName );
        projectSelectedEvent.fire( new ProjectSelectedEvent( projectName ) );
    }

    private void setActiveProject( String projectName ) {
        for ( Project project : projects ) {
            if ( projectName.equalsIgnoreCase( project.getName() ) ) {
                project.setSelected( true );
            } else {
                project.setSelected( false );
            }
        }
        this.updateView();
    }
}