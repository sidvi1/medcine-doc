package ru.sidvi.medcine.model;

import ru.sidvi.medcine.model.Entity.MedicalRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

// ListDirectoryModel.java
// Model
// This model is completely independent of the user interface.
// It could as easily be used by a command line or web interface.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final ArrayList<MedicalRecord> listDirectory = new ArrayList<>();
    /**
     * Индекс выбранной записи
     */
    private int selectedIndex;

    public ListModel() {
        //TODO: удалить STUB
        listDirectory.add(new MedicalRecord("fasdfasdf"));
        listDirectory.add(new MedicalRecord("yhaasdfasd"));
        listDirectory.add(new MedicalRecord("ass"));
        listDirectory.add(new MedicalRecord("awertyesrgesr"));
        listDirectory.add(new MedicalRecord("adfgsdfg"));
    }

    public void addDirectory(final String directory) {
        int index = listDirectory.size();

        // TODO: connect to entity
        MedicalRecord record = new MedicalRecord();
        record.setId((int) (Math.random() * 100));
        record.setDocDate(new Date());
        record.setName(directory);

        listDirectory.add(record);

        fireTableRowsInserted(index, index);
    }

    public void removeDirectory(String directory) {
        int index = listDirectory.lastIndexOf(directory);
        if (index >= 0) {
            listDirectory.remove(directory);
            fireTableRowsDeleted(index, index);
        }
    }

    public void removeMultipleDirectory(int... indices) {
        if (indices.length > 0) {
            for (int i = indices.length - 1; i >= 0; i--) {
                listDirectory.remove(indices[i]);
            }
            fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
        }
    }

    @Override
    public int getRowCount() {
        return listDirectory.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MedicalRecord record = listDirectory.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return record.getId();
            case 1:
                return record.getDocDate();
            case 2:
                return record.getName();
        }

        throw new IllegalArgumentException();
    }


    public void setSelectedRow(int index) {
        this.selectedIndex = index;
    }

    public MedicalRecord getSelected() {
        return this.listDirectory.get(this.selectedIndex);
    }
}
