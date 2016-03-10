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

package org.uberfire.client.perspectives;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.annotations.WorkbenchPanel;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.workbench.panels.UFFlowPanel;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnOpen;

@Templated
@WorkbenchPerspective(identifier = "MoodPerspective")
public class MoodPerspective extends Composite {

    @DataField
    @WorkbenchPanel(parts = "MoodScreen")
    UFFlowPanel moodScreen = new UFFlowPanel( 100 );

    @DataField
    @WorkbenchPanel(parts = "MoodListenerScreen")
    UFFlowPanel moodListener = new UFFlowPanel( 100 );

    @OnOpen
    public void onOpen() {
        Window.alert( "On Open" );
    }

    @OnClose
    public void OnClose() {
        Window.alert( "On Close" );
    }
}