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

package org.uberfire.client.screens.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;

import javax.annotation.PostConstruct;

public class NewProjectView extends Composite
        implements NewProjectPresenter.View {

    interface Binder
            extends
            UiBinder<Widget, NewProjectView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @UiField
    Modal popup;

    @UiField
    TextBox projectName;

    @UiField
    Button addGroup;

    @UiField
    Button cancel;

    private NewProjectPresenter presenter;

    @Override
    public void init( NewProjectPresenter presenter ) {
        this.presenter = presenter;
    }

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );
        cancel.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.close();
            }
        } );
        addGroup.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.newProject( projectName.getText() );
            }
        } );
    }

    @Override
    public void show() {
        popup.show();
    }

    @Override
    public void hide() {
        popup.hide();
        projectName.setText( "" );
    }

}