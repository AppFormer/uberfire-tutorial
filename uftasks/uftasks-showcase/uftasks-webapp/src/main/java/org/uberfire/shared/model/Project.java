package org.uberfire.shared.model;

public class Project {

    private final String name;
    private boolean selected;

    public Project( String name ) {
        this.name = name;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected( boolean selected ) {
        this.selected = selected;
    }

}
