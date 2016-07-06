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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Event;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.jboss.errai.common.client.dom.Button;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.SinkNative;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class NewFolderView implements NewFolderPresenter.View {

    private NewFolderPresenter presenter;

    private Modal modal;

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

    @Override
    public void init( NewFolderPresenter presenter ) {
        this.presenter = presenter;
        createModal();
    }

    private void createModal() {
        this.modal = GWT.create( Modal.class );
        final ModalBody body = GWT.create( ModalBody.class );
        body.add( ElementWrapperWidget.getWidget( view ) );
        modal.add( body );
    }

    @Override
    public void show() {
        modal.show();
    }

    @Override
    public void hide() {
        modal.hide();
        folderNameTextBox.setValue( "" );
    }

    @SinkNative( Event.ONCLICK )
    @EventHandler( "ok-button" )
    public void addFolder( Event event ) {
        presenter.newFolder( folderNameTextBox.getValue() );
    }

    @EventHandler( "cancel-button" )
    public void cancel( Event event ) {
        presenter.close();
    }

    @Override
    public HTMLElement getElement() {
        return view;
    }
}