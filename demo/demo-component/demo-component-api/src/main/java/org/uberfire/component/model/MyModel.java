package org.uberfire.component.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class MyModel {

    private String value;

    public MyModel() {
    }

    public MyModel( String value ) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }
}
