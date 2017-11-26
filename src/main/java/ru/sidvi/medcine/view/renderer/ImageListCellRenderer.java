// ImageListCellRenderer.java

package ru.sidvi.medcine.view.renderer;

import ru.sidvi.medcine.model.Entity.MedicalRecord;

import java.awt.Component;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

/**
 * Description
 *
 * @author Fabien Ipseiz
 *
 */
public class ImageListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(final JList<?> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {

        MedicalRecord val = (MedicalRecord) value;
        value = val.getId() + " " + val.getDocDate() + " " + val.getName();

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}
