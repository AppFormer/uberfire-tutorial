package org.uberfire.shared.events;

public class TaskCreated {

    private final String project;
    private final String folder;
    private final String task;

    public TaskCreated( String project,
                        String folder,
                        String task ) {

        this.project = project;
        this.folder = folder;
        this.task = task;
    }

    public String getProject() {
        return project;
    }
}
