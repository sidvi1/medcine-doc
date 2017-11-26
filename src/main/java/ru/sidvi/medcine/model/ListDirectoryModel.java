package ru.sidvi.medcine.model;

import ru.sidvi.medcine.model.Entity.MedicalRecord;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.AbstractListModel;

// ListDirectoryModel.java
// Model
// This model is completely independent of the user interface.
// It could as easily be used by a command line or web interface.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListDirectoryModel extends AbstractListModel<Object> {
	
	private static final long serialVersionUID = 1L;

    private final ArrayList<MedicalRecord> listDirectory = new ArrayList<>();

    public MedicalRecord getElementAt(int index) {
        return listDirectory.get(index);
	}
	
	public int getSize() {
		return listDirectory.size();
	}

    public void addDirectory(final String directory) {
        int index=listDirectory.size();

        // TODO: connect to entity
        MedicalRecord record = new MedicalRecord();
        record.setId((int) (Math.random() * 100));
        record.setDocDate(new Date());
        record.setName(directory);

        listDirectory.add(record);
        fireIntervalAdded(this, index, index);
	}
	
	public void removeDirectory(String directory) {
		int index = listDirectory.lastIndexOf(directory);
		if (index >= 0) {
			listDirectory.remove(directory);
			fireIntervalRemoved(this, index, index);
		}
	}

	public void removeMultipleDirectory(int... indices) {
		if (indices.length > 0) {
			for (int i = indices.length-1; i >= 0; i--) {
				listDirectory.remove(indices[i]);
			}
			fireIntervalRemoved(this, indices[0], indices[indices.length-1]);
		}
	}
}
