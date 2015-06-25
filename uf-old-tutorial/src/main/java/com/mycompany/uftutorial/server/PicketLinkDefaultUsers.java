/**
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.uftutorial.server;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.picketlink.authentication.event.PreAuthenticateEvent;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

@ApplicationScoped
public class PicketLinkDefaultUsers {

  @Inject
  private PartitionManager partitionManager;

  private boolean alreadyDone = false;

  public synchronized void create( @Observes PreAuthenticateEvent event ) {
    if ( alreadyDone ) {
      return;
    }

    alreadyDone = true;

    final IdentityManager identityManager = partitionManager.createIdentityManager();
    final RelationshipManager relationshipManager = partitionManager.createRelationshipManager();

    User admin = new User("admin");

    admin.setEmail("john@doe.com");
    admin.setFirstName("John");
    admin.setLastName("Doe");

    User regular = new User("regular");

    regular.setEmail("regular@example.com");
    regular.setFirstName("Regular");
    regular.setLastName("User");

    identityManager.add(admin);
    identityManager.add(regular);
    identityManager.updateCredential(admin, new Password("admin"));
    identityManager.updateCredential(regular, new Password("123"));

    Role roleDeveloper = new Role("simple");
    Role roleAdmin = new Role("admin");

    identityManager.add(roleDeveloper);
    identityManager.add(roleAdmin);

    relationshipManager.add(new Grant(admin, roleDeveloper));
    relationshipManager.add(new Grant(admin, roleAdmin));
  }

}
