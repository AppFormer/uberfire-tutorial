package com.mycompany.uftutorial.client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.uberfire.client.workbench.Header;
import org.uberfire.client.workbench.widgets.toolbar.WorkbenchToolBarPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class AppToolBar extends Composite implements Header {

  @Inject
  private WorkbenchToolBarPresenter toolBarPresenter;

  @Override
  public Widget asWidget() {
    return toolBarPresenter.getView().asWidget();
  }

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public String getId() {
    return getClass().getName();
  }

}
