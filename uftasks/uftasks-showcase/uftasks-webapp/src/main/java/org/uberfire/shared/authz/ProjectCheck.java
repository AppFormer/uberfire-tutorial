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

package org.uberfire.shared.authz;

import org.jboss.errai.security.shared.api.identity.User;
import org.uberfire.security.Resource;
import org.uberfire.security.ResourceType;
import org.uberfire.security.authz.AuthorizationManager;
import org.uberfire.security.impl.authz.ResourceCheckImpl;

public class ProjectCheck extends ResourceCheckImpl<ProjectCheck> {

    public ProjectCheck(AuthorizationManager authorizationManager, ResourceType resourceType, User user) {
        super(authorizationManager, resourceType, user);
    }

    public ProjectCheck(AuthorizationManager authorizationManager, Resource resource, User user) {
        super(authorizationManager, resource, user);
    }

    public ProjectCheck create() {
        return super.action(ProjectAction.CREATE);
    }

    public ProjectCheck read() {
        return super.action(ProjectAction.READ);
    }

    public ProjectCheck edit() {
        return super.action(ProjectAction.EDIT);
    }
}