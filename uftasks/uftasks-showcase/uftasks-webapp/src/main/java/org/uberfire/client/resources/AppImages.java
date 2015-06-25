package org.uberfire.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * GWT managed images for Workbench
 */
public interface AppImages extends ClientBundle {

    @Source("images/uf_user_logo.png")
    ImageResource ufUserLogo();

    @Source("images/uf.png")
    ImageResource ufBrandLogo();

}
