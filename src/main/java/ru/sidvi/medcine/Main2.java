package ru.sidvi.medcine;

import ru.sidvi.medcine.model.ListDirectoryModel;
import ru.sidvi.medcine.view.EditDirectoryView;

import java.text.ParseException;

/**
 * @author Vitaly Sidorov
 */
public class Main2 {


    public static void main(String[] args) throws ParseException {
        ListDirectoryModel model = new ListDirectoryModel();
        EditDirectoryView view = new EditDirectoryView(model);

        view.pack();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

}
