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

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.shared.events.TaskCreated;
import org.uberfire.shared.events.TaskDone;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@WorkbenchScreen(identifier = "DashboardPresenter")
public class DashboardPresenter {

    public interface View extends UberView<DashboardPresenter> {

        void addProject( String project,
                         String tasksCreated,
                         String tasksDone );

        void clear();
    }

    @Inject
    private View view;

    private Map<String, ProjectTasksCounter> projectTasksCounter = new HashMap<String, ProjectTasksCounter>();

    @WorkbenchPartTitle
    public String getTitle() {
        return "Dashboard";
    }

    @WorkbenchPartView
    public UberView<DashboardPresenter> getView() {
        return view;
    }

    @OnOpen
    public void onOpen() {
        updateView();
    }

    private void updateView() {
        view.clear();
        for ( String project : projectTasksCounter.keySet() ) {
            ProjectTasksCounter projectTasksCounter = this.projectTasksCounter.get( project );
            view.addProject( project, projectTasksCounter.getTasksCreated(), projectTasksCounter.getTasksDone() );
        }
    }

    public void taskCreated( @Observes TaskCreated taskCreated ) {
        ProjectTasksCounter projectTasksCounter = getProjectTasksCounter( taskCreated.getProject() );
        projectTasksCounter.taskCreated();
    }

    public void taskDone( @Observes TaskDone taskDone ) {
        ProjectTasksCounter projectTasksCounter = getProjectTasksCounter( taskDone.getProject() );
        projectTasksCounter.taskDone();
    }

    public ProjectTasksCounter getProjectTasksCounter( String projectName ) {
        ProjectTasksCounter projectTasksCounter = this.projectTasksCounter.get( projectName );
        if ( projectTasksCounter == null ) {
            projectTasksCounter = new ProjectTasksCounter();
            this.projectTasksCounter.put( projectName, projectTasksCounter );
        }
        return projectTasksCounter;
    }

    private class ProjectTasksCounter {

        int tasksDone;
        int tasksCreated;

        public void taskDone() {
            tasksDone++;
            tasksCreated--;
        }

        public void taskCreated() {
            tasksCreated++;
        }

        public String getTasksDone() {
            return String.valueOf( tasksDone );
        }

        public String getTasksCreated() {
            return String.valueOf( tasksCreated );
        }
    }

}