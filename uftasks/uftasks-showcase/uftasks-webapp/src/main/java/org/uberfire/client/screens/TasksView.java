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
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.util.List;

@Dependent
public class TasksView extends Composite implements TasksPresenter.View {

    @UiField
    Button newFolder;

    @UiField
    FlowPanel tasks;

    private TasksPresenter presenter;

    interface Binder
            extends
            UiBinder<Widget, TasksView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @Override
    public void init( final TasksPresenter presenter ) {
        this.presenter = presenter;
        this.newFolder.setVisible( false );
    }

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );

    }

    @Override
    public void activateNewFolder() {
        newFolder.setVisible( true );
        newFolder.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showNewFolder();
            }
        } );
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void newFolder( String folderName,
                           Integer numberOfTasks,
                           List<String> tasksList ) {

        ListGroup folder = GWT.create( ListGroup.class );
        folder.add( generateFolderTitle( folderName, numberOfTasks ) );

        for ( String task : tasksList ) {
            folder.add( generateTask( folderName, task ) );
        }
        folder.add( generateNewTask( folderName ) );
        tasks.add( folder );
    }

    private ListGroupItem generateNewTask( String folderName ) {
        ListGroupItem newTask = GWT.create( ListGroupItem.class );
        InputGroup inputGroup = GWT.create( InputGroup.class );
        inputGroup.add( createTextBox( folderName ) );
        newTask.add( inputGroup );
        return newTask;
    }

    private TextBox createTextBox( final String folderName ) {
        final TextBox taskText = GWT.create( TextBox.class );
        taskText.addKeyDownHandler( new KeyDownHandler() {
            @Override
            public void onKeyDown( KeyDownEvent event ) {
                if ( event.getNativeKeyCode() == KeyCodes.KEY_ENTER ) {
                    presenter.createTask( folderName, taskText.getText() );
                }
            }
        } );
        taskText.setWidth( "400" );
        taskText.setPlaceholder( "New task..." );
        return taskText;
    }

    private ListGroupItem generateFolderTitle( String name,
                                               Integer numberOfTasks ) {
        ListGroupItem folderTitle = GWT.create( ListGroupItem.class );
        folderTitle.setText( name );
        folderTitle.setType( ListGroupItemType.INFO );
        Badge number = GWT.create( Badge.class );
        number.setText( String.valueOf( numberOfTasks ) );
        folderTitle.add( number );
        return folderTitle;
    }

    private ListGroupItem generateTask( String folderName,
                                        String taskText ) {
        ListGroupItem tasks = GWT.create( ListGroupItem.class );
        tasks.add( createTaskCheckbox( folderName, taskText ) );
        return tasks;
    }

    private InlineCheckBox createTaskCheckbox( final String folderName,
                                               final String taskText ) {
        InlineCheckBox checkBox = GWT.create( InlineCheckBox.class );
        checkBox.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.doneTask( folderName, taskText );
            }
        } );
        checkBox.setText( taskText );
        return checkBox;
    }

}