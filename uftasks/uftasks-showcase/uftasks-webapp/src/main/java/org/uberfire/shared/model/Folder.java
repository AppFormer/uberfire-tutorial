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

package org.uberfire.shared.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private final String name;

    private List<String> tasks = new ArrayList<String>();

    public Folder( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void addTask( String task ) {
        tasks.add( task );
    }

    public void removeTask( String taskText ) {
        tasks.remove( taskText );
    }
}