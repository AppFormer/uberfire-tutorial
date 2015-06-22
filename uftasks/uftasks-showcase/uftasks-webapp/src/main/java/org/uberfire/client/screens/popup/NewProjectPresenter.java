package org.uberfire.client.screens.popup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.uberfire.client.mvp.UberView;
import org.uberfire.client.screens.ProjectsPresenter;

@Dependent
public class NewProjectPresenter {

    private ProjectsPresenter projectsPresenter;

    public interface View extends UberView<NewProjectPresenter> {
        void show();
        void hide();
    }

    @Inject
    private View view;

    @PostConstruct
    public void setup(){
        view.init( this );
    }

    public void show( ProjectsPresenter projectsPresenter ){
        this.projectsPresenter = projectsPresenter;
        view.show();
    }

    public void newProject( String projectName ) {
        projectsPresenter.createNewProject( projectName );
        view.hide();
    }

    public void close() {
        view.hide();
    }

}
