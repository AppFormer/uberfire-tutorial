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

public class NewFolderView extends Composite
        implements NewFolderPresenter.View {

    interface Binder
            extends
            UiBinder<Widget, NewFolderView> {
    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @UiField
    Modal popup;

    @UiField
    TextBox folderName;

    @UiField
    Button addFolder;

    @UiField
    Button cancel;

    private NewFolderPresenter presenter;

    @Override
    public void init( NewFolderPresenter presenter ) {
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
        addFolder.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.newFolder( folderName.getText() );
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
        folderName.setText( "" );
    }

}
