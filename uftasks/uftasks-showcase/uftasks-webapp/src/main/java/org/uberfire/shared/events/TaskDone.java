package org.uberfire.shared.events;

public class TaskDone {

    private final String project;
    private final String folder;
    private final String task;

    public TaskDone( String project,
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
