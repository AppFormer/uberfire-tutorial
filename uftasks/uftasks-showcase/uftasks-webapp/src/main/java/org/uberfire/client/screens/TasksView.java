package org.uberfire.client.screens;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.InlineCheckBox;
import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ListGroupItemType;

@Dependent
public class TasksView extends Composite implements TasksPresenter.View {

    @UiField
    Button newFolder;

    @UiField
    FlowPanel tasks;

    private TasksPresenter presenter;

    interface Binder
            extends
            UiBinder<Widget, TasksView> {

    }

    private static Binder uiBinder = GWT.create( Binder.class );

    @Override
    public void init( final TasksPresenter presenter ) {
        this.presenter = presenter;
        this.newFolder.setVisible( false );
    }

    @PostConstruct
    public void setup() {
        initWidget( uiBinder.createAndBindUi( this ) );

    }

    @Override
    public void activateNewFolder( ) {
        newFolder.setVisible( true );
        newFolder.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showNewFolder();
            }
        } );
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void newFolder( String folderName,
                           Integer numberOfTasks,
                           List<String> tasksList ) {

        ListGroup folder = GWT.create( ListGroup.class );
        folder.add( generateFolderTitle( folderName, numberOfTasks ) );
        tasks.add( folder );
    }

    private ListGroupItem generateFolderTitle( String name,
                                               Integer numberOfTasks ) {
        ListGroupItem folderTitle = GWT.create( ListGroupItem.class );
        folderTitle.setText( name );
        folderTitle.setType( ListGroupItemType.INFO );
        Badge number = GWT.create( Badge.class );
        number.setText( String.valueOf( numberOfTasks ) );
        folderTitle.add( number );
        return folderTitle;
    }

}