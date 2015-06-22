package org.uberfire.shared.events;

public class ProjectSelectedEvent {

    private final String name;

    public ProjectSelectedEvent( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
