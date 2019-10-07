package org.gwtproject.editor.client.adapters;

import org.gwtproject.core.client.EntryPoint;
import org.gwtproject.user.client.ui.Button;
import org.gwtproject.user.client.ui.RootPanel;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 9/12/19
 */
public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new Button("Press me !"));
    }
}
