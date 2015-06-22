package org.uberfire.client.screens;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.UberView;
import org.uberfire.client.screens.popup.NewFolderPresenter;
import org.uberfire.shared.events.ProjectSelectedEvent;

@ApplicationScoped
@WorkbenchScreen(identifier = "TasksPresenter")
public class TasksPresenter {

    public interface View extends UberView<TasksPresenter> {

        void activateNewFolder();

        void clearTasks();

        void newFolder( String name,
                        Integer size,
                        List<String> tasks );
    }

    @Inject
    private View view;

    private String currentSelectedProject;

    @Inject
    private NewFolderPresenter newFolderPresenter;

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
        updateView( null );
    }

    public void showNewFolder() {
        newFolderPresenter.show( this );
    }

    private void updateView( String folderName ) {
        view.clearTasks();
        if(folderName!=null){
            view.newFolder( folderName, 0, new ArrayList<String>() );
        }
    }

    public void newFolder( String folderName ) {
        updateView( folderName );
    }

}
