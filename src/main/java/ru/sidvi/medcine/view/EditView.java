package ru.sidvi.medcine.view;

import net.miginfocom.swing.MigLayout;
import ru.sidvi.medcine.ButtonsListener;
import ru.sidvi.medcine.model.ListModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatModified;
import java.text.ParseException;
import java.util.Date;

// ListDirectoryView.java
// Presentation only.  No user actions.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class EditView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JButton saveButton;
    private final JButton cancelButton;

    private final JTextField documentDateElement;
    private final JTextField hospitalElement;
    private final JComboBox docTypeElement;

    /**
     * Create the frame.
     */
    public EditView(ListModel dirModel) throws ParseException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        saveButton = new JButton("Сохранить");
        cancelButton = new JButton("Отмена");

        documentDateElement = new JFormattedTextField(new MaskFormatter("####.##.##"));
        hospitalElement = new JTextField();
        docTypeElement = new JComboBox();


        JPanel panel = new JPanel(new MigLayout("wrap 2", "[][grow,fill]"));

        panel.add(new JLabel("Дата документа    "));
        panel.add(this.documentDateElement, "width 100::100");
        panel.add(new JLabel("Мед. Учереждение"));
        panel.add(hospitalElement, "width 100::600");
        panel.add(new JLabel("Тип документа"));
        panel.add(docTypeElement, "width 100::200");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        panel.add(buttonsPanel, "align right");

        setContentPane(panel);
    }

    public void addButtonsListener(final ButtonsListener l) {

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                l.addPerformed(e);

            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                l.deletePerformed(e);

            }
        });
    }

    /**
     * Полчить название мед. учереждения
     *
     * @return название медучереждения
     */
    public String getHospital() {
        return hospitalElement.getText();
    }

    /**
     * Получить дату документа
     *
     * @return дата документа
     */
    public Date getDocDate() {
        DateFormatModified df = new DateFormatModified("yyyy.MM.dd");
        return df.parse(documentDateElement.getText());
    }
}
