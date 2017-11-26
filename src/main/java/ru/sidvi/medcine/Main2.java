package ru.sidvi.medcine;

import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.view.EditView;

import java.text.ParseException;

/**
 * @author Vitaly Sidorov
 */
public class Main2 {


    public static void main(String[] args) throws ParseException {
        ListModel model = new ListModel();
        EditView view = new EditView(model);

        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

}
