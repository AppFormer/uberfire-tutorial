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

import org.jboss.errai.common.client.dom.Span;
import org.jboss.errai.common.client.dom.UnorderedList;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Templated
public class DashboardItem implements IsElement {

    @Inject
    @DataField( "dashboard-item" )
    UnorderedList dashboardItem;

    @Inject
    @DataField( "project-name" )
    Span projectName;

    @Inject
    @DataField( "todo" )
    Span todo;


    @Inject
    @DataField( "done" )
    Span done;


    public void init( String projectName, String todo, String done ) {

        this.projectName.setTextContent( projectName.toUpperCase() );
        this.todo.setTextContent( todo );
        this.done.setTextContent( done );

    }

}