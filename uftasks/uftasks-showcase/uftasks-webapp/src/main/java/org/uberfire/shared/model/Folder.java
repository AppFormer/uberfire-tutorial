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
