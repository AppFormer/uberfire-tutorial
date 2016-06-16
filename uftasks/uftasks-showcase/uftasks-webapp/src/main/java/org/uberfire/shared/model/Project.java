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

package org.uberfire.shared.model;

import java.util.List;

import org.uberfire.security.Resource;
import org.uberfire.security.ResourceType;
import org.uberfire.shared.authz.UTTasksResourceType;

public class Project implements Resource {

    private final String name;
    private boolean selected;

    public Project( String name ) {
        this.name = name;
        this.selected = false;
    }

    @Override
    public String getIdentifier() {
        return name;
    }

    @Override
    public ResourceType getResourceType() {
        return UTTasksResourceType.PROJECT;
    }

    @Override
    public List<Resource> getDependencies() {
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected( boolean selected ) {
        this.selected = selected;
    }
}