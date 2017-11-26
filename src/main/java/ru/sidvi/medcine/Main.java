package ru.sidvi.medcine;
// ListDirectotyMain.java

import ru.sidvi.medcine.controller.ListDirectoryController;
import ru.sidvi.medcine.model.ListDirectoryModel;
import ru.sidvi.medcine.view.ListDirectoryView;

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

		ListDirectoryModel model = new ListDirectoryModel();
		ListDirectoryView view = new ListDirectoryView(model);
		@SuppressWarnings("unused")
		ListDirectoryController controller = new ListDirectoryController(model, view);
				
		view.pack();
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
}
