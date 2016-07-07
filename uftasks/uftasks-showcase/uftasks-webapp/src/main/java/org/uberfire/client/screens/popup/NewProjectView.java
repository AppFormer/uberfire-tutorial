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
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NewProjectView implements NewProjectPresenter.View {

    private NewProjectPresenter presenter;

    private Modal modal;

    @Inject
    NewProjectContentView contentView;

    @Override
    public void init( NewProjectPresenter presenter ) {
        this.presenter = presenter;
        contentView.init( projectName -> presenter.newProject( projectName ),
                          () -> presenter.close() );
        createModal();
    }

    private void createModal() {
        this.modal = GWT.create( Modal.class );
        final ModalBody body = GWT.create( ModalBody.class );
        body.add( ElementWrapperWidget.getWidget( contentView.getElement() ) );
        modal.add( body );
    }

    @Override
    public void show() {
        modal.show();
    }

    @Override
    public void hide() {
        contentView.clearContent();
        modal.hide();
    }

}