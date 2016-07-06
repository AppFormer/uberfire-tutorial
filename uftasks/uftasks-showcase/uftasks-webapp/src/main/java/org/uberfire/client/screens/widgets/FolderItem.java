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

package org.uberfire.client.screens.widgets;

import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.*;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class FolderItem implements IsElement {

    @Inject
    @DataField
    UnorderedList folder;

    @Inject
    @DataField
    Span folderName;

    @Inject
    @DataField
    Span numberOfTasks;

    @Inject
    @DataField
    Div tasksList;

    @Inject
    @DataField
    Form newTaskForm;

    @Inject
    @DataField
    Input taskName;

    @Inject
    ManagedInstance<TaskItem> tasks;

    @Override
    public HTMLElement getElement() {
        return folder;
    }

    public void init( String folderName, String numberOfTasks, ParameterizedCommand<String> newTask ) {
        this.folderName.setTextContent( folderName );
        this.numberOfTasks.setTextContent( numberOfTasks );
        newTaskForm.setOnsubmit( e -> {
            e.preventDefault();
            newTask.execute( taskName.getValue() );
        } );
    }

    public void createTask( String taskTitle, Command doneCommand ) {
        TaskItem taskItem = tasks.get();
        taskItem.init( taskTitle, doneCommand );
        tasksList.appendChild( taskItem.getElement() );
    }
}