package ru.sidvi.medcine.controller;

import ru.sidvi.medcine.EditButtonsListener;
import ru.sidvi.medcine.ListButtonsListener;
import ru.sidvi.medcine.model.entity.MedicalRecord;
import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.view.EditView;
import ru.sidvi.medcine.view.ListView;

import java.awt.event.ActionEvent;

// ListDirectoryController.java
// Handles user interaction with listeners.
// Calls View and Model as needed.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ViewController implements ListButtonsListener, EditButtonsListener {

    private final ListModel model;
    private final ListView listView;
    private final EditView editView;

    public ViewController(ListModel model, ListView listView, EditView editView) {
        this.model = model;
        this.listView = listView;
        this.editView = editView;
        //... Add listeners to the view.
        editView.addButtonsListener(this);
        listView.addButtonsListener(this);
    }

    public void deletePerformed(ActionEvent e) {
        model.removeMultiple(listView.getSelectedRows());
    }

    public void showEditPerformed(int index) {
        model.setSelectedRow(index);

        editView.pack();
        editView.setModal(true);
        editView.setLocationRelativeTo(null);
        editView.setVisible(true);
    }

    public void savePerformed() {
        MedicalRecord selected = model.getSelectedOrCreateNew();
        selected.setName(editView.getHospital());
        selected.setDocDate(editView.getDocDate());
        selected.setId(editView.getId());

        model.save(selected);
    }
}

