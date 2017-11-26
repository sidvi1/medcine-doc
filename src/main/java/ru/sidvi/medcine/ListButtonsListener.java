package ru.sidvi.medcine;

import java.awt.event.ActionEvent;

public interface ListButtonsListener {

    void deletePerformed(ActionEvent e);

    void showEditPerformed(int selectedRow);
}
