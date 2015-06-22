package org.uberfire.client.screens.popup;

import javax.annotation.PostConstruct;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;

public class NewProjectView extends Composite
        implements NewProjectPresenter.View {

    interface Binder
            extends
            UiBinder<Widget, NewProjectView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @UiField
    Modal popup;

    @UiField
    TextBox projectName;

    @UiField
    Button addGroup;

    @UiField
    Button cancel;

    private NewProjectPresenter presenter;


    @Override
    public void init( NewProjectPresenter presenter ) {
        this.presenter = presenter;
    }

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );
        cancel.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.close();
            }
        } );
        addGroup.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.newProject( projectName.getText() );
            }
        } );
    }

    @Override
    public void show() {
        popup.show();
    }

    @Override
    public void hide() {
        popup.hide();
        projectName.setText( "" );
    }

}
