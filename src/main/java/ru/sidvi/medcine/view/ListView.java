package ru.sidvi.medcine.view;

import ru.sidvi.medcine.ListButtonsListener;
import ru.sidvi.medcine.TextTranslation;
import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.model.format.Formatters;
import ru.sidvi.medcine.support.GuiSupport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

// ListDirectoryView.java
// Presentation only.  No user actions.

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class ListView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField searchElement;
    private final JTable viewTable;

    /**
     * Create the frame.
     */
    public ListView(final ListModel model) {

        // Get translation object and set default locale:
        TextTranslation t = TextTranslation.getInstance();

        GuiSupport.registerCloseOnEsc(getRootPane(), this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setName(getTitle());
        setContentPane(contentPane);

        // Create input field row:
        JLabel entryLabel = new JLabel("Фильтр");
        searchElement = new JTextField(40);
        searchElement.setName("inputText");
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(entryLabel);
        inputPanel.add(searchElement);
        JPanel inputRowPanel = new JPanel(new BorderLayout());
        inputRowPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        inputRowPanel.add(inputPanel);
        contentPane.add(inputRowPanel, BorderLayout.NORTH);

        // Create list panel:
        viewTable = new JTable(model);
        viewTable.setName("list");
        viewTable.setFillsViewportHeight(true);
        TableColumn idColumn = viewTable.getColumnModel().getColumn(0);
        idColumn.setMaxWidth(50);
        idColumn.setHeaderValue("Id");

        final TableColumn dateColumn = viewTable.getColumnModel().getColumn(1);
        dateColumn.setMaxWidth(100);
        dateColumn.setMinWidth(100);
        dateColumn.setHeaderValue("Дата");
        dateColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText(Formatters.formatDate((Date) value));
            }
        });

        TableColumn hospitalColumn = viewTable.getColumnModel().getColumn(2);
        hospitalColumn.setHeaderValue("Мед. учереждение");
        hospitalColumn.setMinWidth(300);

        getContentPane().add(new JScrollPane(viewTable));
    }

    public void addButtonsListener(final ListButtonsListener l) {
        // Select text if row is double-clicked:
        viewTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && viewTable.getSelectedRow() != -1) {
                    l.showEditPerformed(viewTable.getSelectedRow());
                }
            }
        });

        viewTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    l.showEditPerformed(viewTable.getSelectedRow());
                }
            }
        });

        GuiSupport.registerListenerOnCtrlN(getRootPane(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.showEditPerformed(-1);
            }
        });
    }

    public int[] getSelectedRows() {
        return viewTable.getSelectedRows();
    }

    /**
     * @return the inputText
     */
    public String getSearchText() {
        return searchElement.getText();
    }
}
