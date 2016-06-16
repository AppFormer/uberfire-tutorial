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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.container.IOC;
import org.uberfire.rpc.SessionInfo;
import org.uberfire.security.authz.AuthorizationManager;
import org.uberfire.shared.model.Project;

@ApplicationScoped
public class UFTasksController {

    public static UFTasksController get() {
        return IOC.getBeanManager().lookupBean(UFTasksController.class).getInstance();
    }

    AuthorizationManager authorizationManager;
    SessionInfo sessionInfo;

    @Inject
    public UFTasksController(AuthorizationManager authorizationManager, SessionInfo sessionInfo) {
        this.authorizationManager = authorizationManager;
        this.sessionInfo = sessionInfo;
    }

    public ProjectCheck projects() {
        return new ProjectCheck(authorizationManager, UTTasksResourceType.PROJECT, sessionInfo.getIdentity());
    }

    public ProjectCheck project(Project project) {
        return new ProjectCheck(authorizationManager, project, sessionInfo.getIdentity());
    }

    public boolean sayhello() {
        return authorizationManager.authorize("uftasks.welcome", sessionInfo.getIdentity());
    }
}
