package org.uberfire.client.screens;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.LinkedGroup;
import org.gwtbootstrap3.client.ui.LinkedGroupItem;

@Dependent
public class ProjectsView extends Composite implements ProjectsPresenter.View {

    interface Binder
            extends
            UiBinder<Widget, ProjectsView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    private ProjectsPresenter presenter;


    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    @Override
    public void init( ProjectsPresenter presenter ) {
        this.presenter = presenter;
    }

}