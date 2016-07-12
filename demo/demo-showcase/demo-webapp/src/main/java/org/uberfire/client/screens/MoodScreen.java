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

import org.jboss.errai.common.client.dom.Form;
import org.jboss.errai.common.client.dom.Input;
import org.jboss.errai.ui.client.local.api.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.shared.Mood;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Dependent
@Templated
@WorkbenchScreen( identifier = "MoodScreen" )
public class MoodScreen implements IsElement {

    @Inject
    @DataField
    Form moodForm;

    @Inject
    @DataField
    Input moodTextBox;

    @Inject
    Event<Mood> moodEvent;

    @WorkbenchPartTitle
    public String getScreenTitle() {
        return "Change Mood";
    }


    @PostConstruct
    public void init() {
        moodForm.setOnsubmit( e -> {
            e.preventDefault();
            moodEvent.fire( new Mood( moodTextBox.getValue() ) );
            moodTextBox.setValue( "" );
        } );
    }

    @WorkbenchPartView
    public IsElement getView() {
        return this;
    }

}