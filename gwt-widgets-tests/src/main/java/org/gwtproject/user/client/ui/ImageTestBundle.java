package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.ImageResource;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 9/12/19
 */
//@Resource
public interface ImageTestBundle extends ClientBundle {
    public ImageResource prettyPiccy();

    @Source("prettyPiccy.png")
    @ImageResource.ImageOptions(preventInlining = true)
    ImageResource prettyPiccyStandalone();
}
