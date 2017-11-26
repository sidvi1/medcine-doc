package ru.sidvi.medcine;
// ListDirectotyMain.java

import ru.sidvi.medcine.controller.ViewController;
import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.view.EditView;
import ru.sidvi.medcine.view.ListView;

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class Main {

    //... Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
    public static void main(String[] args) {

        ListModel model = new ListModel();
        ListView listView = new ListView(model);
        EditView editView = new EditView(model);
        @SuppressWarnings("unused")
        ViewController controller = new ViewController(model, listView, editView);

        listView.pack();
        listView.setLocationRelativeTo(null);
        listView.setVisible(true);
    }
}
