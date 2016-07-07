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

import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.Document;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.screens.widgets.FolderItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import static org.jboss.errai.common.client.dom.DOMUtil.removeAllChildren;

@Dependent
@Templated
public class TasksView implements TasksPresenter.View {

    private TasksPresenter presenter;

    @Inject
    Document document;

    @Inject
    @DataField( "view" )
    Div view;

    @Inject
    @DataField( "new-folder" )
    Button newFolder;

    @Inject
    @DataField( "tasks" )
    Div tasks;

    @Inject
    ManagedInstance<FolderItem> folders;

    @Override
    public void init( final TasksPresenter presenter ) {
        this.presenter = presenter;
        this.newFolder.setDisabled( true );
    }

    @Override
    public void activateNewFolder() {
        newFolder.setDisabled( false );
    }

    @Override
    public void clearTasks() {
        removeAllChildren( tasks );
    }

    @Override
    public void newFolder( String folderName,
                           String numberOfTasks,
                           List<String> tasksList ) {
        FolderItem folderItem = createFolder( folderName, numberOfTasks );
        createTasks( folderName, tasksList, folderItem );

        tasks.appendChild( folderItem.getElement() );
    }

    private void createTasks( String folderName, List<String> tasksList, FolderItem folderItem ) {
        for ( String task : tasksList ) {
            folderItem.createTask( task,
                                   () -> presenter.doneTask( folderName, task ) );
        }
    }

    private FolderItem createFolder( String folderName, String numberOfTasks ) {
        FolderItem folderItem = folders.get();
        folderItem.init( folderName,
                         numberOfTasks,
                         newTaskText -> presenter.createTask( folderName, newTaskText ) );
        return folderItem;
    }


    @EventHandler( "new-folder" )
    public void newFolderClick( ClickEvent event ) {
        presenter.showNewFolder();
    }

    @Override
    public HTMLElement getElement() {
        return view;
    }
}