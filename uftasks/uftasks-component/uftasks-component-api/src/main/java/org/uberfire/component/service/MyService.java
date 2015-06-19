package org.uberfire.component.service;

import org.uberfire.component.model.MyModel;
import org.jboss.errai.bus.server.annotations.Remote;

@Remote
public interface MyService {

    MyModel execute( final String param );

}
