package org.uberfire.component.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import org.gwtbootstrap3.client.ui.Label;

@Dependent
public class ComponentView extends Composite implements ComponentPresenter.View {

    private FlowPanel container = new FlowPanel();

    private Label label = new Label( "Empty" );

    private ComponentPresenter presenter;

    @PostConstruct
    public void setup() {
        initWidget( container );
        container.add( label );
    }

    @Override
    public void init( ComponentPresenter presenter ) {
        this.presenter = presenter;
    }

    @Override
    public void setValue( String value ) {
        label.setText( value );
    }
}