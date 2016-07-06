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

package org.uberfire.client.screens.popup;

import com.google.gwt.user.client.Event;
import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class NewFolderContentView implements IsElement {

    @Inject
    @DataField
    Div view;

    @Inject
    @DataField( "folder-name" )
    Input folderNameTextBox;

    @Inject
    @DataField( "ok-button" )
    Button okButton;

    @Inject
    @DataField( "cancel-button" )
    Button cancelButton;

    private ParameterizedCommand<String> addFolder;
    private Command cancel;

    public void init( ParameterizedCommand<String> addFolder, Command cancel ) {
        this.addFolder = addFolder;
        this.cancel = cancel;
    }


    public void clearContent() {
        folderNameTextBox.setValue( "" );
    }

    @SinkNative( Event.ONCLICK )
    @EventHandler( "ok-button" )
    public void addFolder( Event event ) {
        addFolder.execute( folderNameTextBox.getValue() );
    }

    @EventHandler( "cancel-button" )
    public void cancel( Event event ) {
        cancel.execute();
    }

    @Override
    public HTMLElement getElement() {
        return view;
    }
}