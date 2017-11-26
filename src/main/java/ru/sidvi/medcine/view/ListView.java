package ru.sidvi.medcine.view;

import ru.sidvi.medcine.ButtonsListener;
import ru.sidvi.medcine.TextTranslation;
import ru.sidvi.medcine.model.ListModel;
import ru.sidvi.medcine.model.format.Formatters;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
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
public class ListView extends BaseFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField searchElement;
    private final JTable viewTable;

    /**
     * Create the frame.
     */
    public ListView(final ListModel dirModel) {

        // Get translation object and set default locale:
        TextTranslation t = TextTranslation.getInstance();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Register a KeyListener for the searchElement text field.
        searchElement.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    addButton.doClick();
                    searchElement.selectAll();
                }
            }
        });

        searchElement.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchElement.selectAll();
            }
        });

        // Register a DocumentListener for the searchElement text field.
        searchElement.getDocument()
                .addDocumentListener(new DocumentListener() {

                    @Override
                    public void removeUpdate(DocumentEvent arg0) {
                        if (searchElement.getText().isEmpty()) {
//                            addButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void insertUpdate(DocumentEvent arg0) {
//                        addButton.setEnabled(true);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent arg0) {
                    }
                });


        // Create list panel:
        viewTable = new

                JTable(dirModel);
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

        // Select text if row is double-clicked:
        viewTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && viewTable.getSelectedRow() != -1) {
                    EditView editView = new EditView(dirModel);
                    editView.pack();
                    editView.setLocationRelativeTo(null);
                    editView.setVisible(true);
                }
            }
        });

//		// Register a ListSelectionListener for the list panel.
//		viewTable.addRowSelectionInterval(new ListSelectionListener() {
//
//			@Override
//			public void valueChanged(ListSelectionEvent arg0) {
////				if (viewTable.getSelectedIndices().length == 0 ){
////					removeButton.setEnabled(false);
////				} else {
////					removeButton.setEnabled(true);
////				}
//
//			}
//		});

    }

    public void addButtonsListener(final ButtonsListener l) {

//
    }

    /**
     * addButton.addActionListener(new ActionListener() {
     * //
     * //            @Override
     * //            public void actionPerformed(ActionEvent e) {
     * //                l.addPerformed(e);
     * //
     * //            }
     * //        });
     * //
     * //        removeButton.addActionListener(new ActionListener() {
     * //
     * //            @Override
     * //            public void actionPerformed(ActionEvent e) {
     * //                l.deletePerformed(e);
     * //
     * //            }
     * //        });
     *
     * @return the indexes of the selected rows of the list
     */
    public int[] getSelectedText() {
        return viewTable.getSelectedRows();
    }

    /**
     * @return the inputText
     */
    public String getInputText() {
        return searchElement.getText();
    }
}
