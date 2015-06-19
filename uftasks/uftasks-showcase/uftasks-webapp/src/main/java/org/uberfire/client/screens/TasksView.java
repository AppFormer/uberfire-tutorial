package org.uberfire.client.screens;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@Dependent
public class TasksView extends Composite implements TasksPresenter.View {

    private TasksPresenter presenter;

    interface Binder
            extends
            UiBinder<Widget, TasksView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @Override
    public void init( final TasksPresenter presenter ) {
        this.presenter = presenter;
    }

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );

    }
}