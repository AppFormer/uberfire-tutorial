package org.uberfire.client.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.client.screens.popup.NewFolderPresenter;
import org.uberfire.shared.events.ProjectSelectedEvent;
import org.uberfire.shared.model.Folder;

@ApplicationScoped
@WorkbenchScreen(identifier = "TasksPresenter")
public class TasksPresenter {

    public interface View extends UberView<TasksPresenter> {

        void activateNewFolder();

        void clearTasks();

        void newFolder( String name,
                        Integer size,
                        List<String> strings );
    }

    @Inject
    private View view;

    @Inject
    private NewFolderPresenter newFolderPresenter;

    private String currentSelectedProject;

    private Map<String, List<Folder>> foldersPerProject = new HashMap<String, List<Folder>>();

    @WorkbenchPartTitle
    public String getTitle() {
        return "Tasks";
    }

    @WorkbenchPartView
    public UberView<TasksPresenter> getView() {
        return view;
    }

    public void projectSelected( @Observes ProjectSelectedEvent projectSelectedEvent ) {
        this.currentSelectedProject = projectSelectedEvent.getName();
        selectFolder();
    }

    private void selectFolder() {
        view.activateNewFolder();
        updateView();
    }

    public void showNewFolder() {
        newFolderPresenter.show( this );
    }

    public void createTask( String folderName,
                            String task ) {

        Folder folder = getFolder( folderName );
        if ( folder != null ) {
            folder.addTask( task );
        }
        updateView();
    }

    private Folder getFolder( String folderName ) {
        for ( final Folder folder : getFolders() ) {
            if ( folder.getName().equalsIgnoreCase( folderName ) ) {
                return folder;
            }
        }
        return null;
    }

    public void doneTask( String folderName,
                          String taskText ) {
        Folder folder = getFolder( folderName );
        if ( folder != null ) {
            folder.removeTask( taskText );
        }
        updateView();
    }

    private List<Folder> getFolders() {
        List<Folder> folders = foldersPerProject.get( currentSelectedProject );
        if ( folders == null ) {
            folders = new ArrayList<Folder>();
        }
        return folders;
    }

    private void updateView() {
        view.clearTasks();
        for ( final Folder folder : getFolders() ) {
            view.newFolder( folder.getName(), folder.getTasks().size(), folder.getTasks() );
        }
    }

    public void newFolder( String folderName ) {
        List<Folder> folders = getFolders();
        folders.add( new Folder( folderName ) );
        foldersPerProject.put( currentSelectedProject, folders );
        updateView();
    }
}
