package ru.sidvi.medcine.model;

import ru.sidvi.medcine.model.entity.MedicalRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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

    private final ArrayList<MedicalRecord> list = new ArrayList<>();
    /**
     * Индекс выбранной записи
     */
    private int selectedIndex;

    public ListModel() {
        //TODO: удалить STUB
        list.add(new MedicalRecord("fasdfasdf"));
        list.add(new MedicalRecord("yhaasdfasd"));
        list.add(new MedicalRecord("ass"));
        list.add(new MedicalRecord("awertyesrgesr"));
        list.add(new MedicalRecord("adfgsdfg"));
    }

    public void add(MedicalRecord record) {
        int index = list.size();
        list.add(record);
        fireTableRowsInserted(index, index);
    }

    public void removeDirectory(String directory) {
        int index = list.lastIndexOf(directory);
        if (index >= 0) {
            list.remove(directory);
            fireTableRowsDeleted(index, index);
        }
    }

    public void removeMultiple(int... indices) {
        if (indices.length > 0) {
            for (int i = indices.length - 1; i >= 0; i--) {
                list.remove(indices[i]);
            }
            fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MedicalRecord record = list.get(rowIndex);

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

    public MedicalRecord getSelectedOrCreateNew() {
        // если новый элемент
        if (isNew()) {
            return new MedicalRecord();
        }

        return this.list.get(this.selectedIndex);
    }

    public void save(MedicalRecord record) {
        if (isNew()) {
            add(record);
        }
        update(record);
    }

    public void update(MedicalRecord record) {
        list.set(this.selectedIndex, record);
        fireTableRowsInserted(this.selectedIndex, this.selectedIndex);
    }

    private boolean isNew() {
        return this.selectedIndex == -1;
    }
}
