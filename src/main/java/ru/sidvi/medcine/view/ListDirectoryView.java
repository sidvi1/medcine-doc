package ru.sidvi.medcine.view;

import ru.sidvi.medcine.ButtonsListener;
import ru.sidvi.medcine.TextTranslation;
import ru.sidvi.medcine.model.ListDirectoryModel;
import ru.sidvi.medcine.model.format.Formatters;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
public class ListDirectoryView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JButton addButton;
    private final JButton removeButton;
    private final JTextField inputElement;
    private final JTable viewTable;

    /**
     * Create the frame.
     */
    public ListDirectoryView(ListDirectoryModel dirModel) {

        // Get translation object and set default locale:
        TextTranslation t = TextTranslation.getInstance();


        final JFrame that = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setName(getTitle());
        setContentPane(contentPane);

        getRootPane()
                .registerKeyboardAction(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent ke) {
                                                that.dispose();
                                            }
                                        },
                        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                        JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Create input field row:
        JLabel entryLabel = new JLabel(t.get("label.input"));
        inputElement = new JTextField(40);
        inputElement.setName("inputText");
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(entryLabel);
        inputPanel.add(inputElement);
        JPanel inputRowPanel = new JPanel(new BorderLayout());
        inputRowPanel.setBorder(new TitledBorder(t.get("label.title")));
        inputRowPanel.add(inputPanel);
        contentPane.add(inputRowPanel, BorderLayout.NORTH);

        // Register a KeyListener for the inputElement text field.
        inputElement.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addButton.doClick();
                    inputElement.selectAll();
                }
            }
        });

        inputElement.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputElement.selectAll();
            }
        });

        // Register a DocumentListener for the inputElement text field.
        inputElement.getDocument()
                .addDocumentListener(new DocumentListener() {

                    @Override
                    public void removeUpdate(DocumentEvent arg0) {
                        if (inputElement.getText().isEmpty()) {
                            addButton.setEnabled(false);
                        }
                    }

                    @Override
                    public void insertUpdate(DocumentEvent arg0) {
                        addButton.setEnabled(true);
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

        TableColumn dateColumn = viewTable.getColumnModel().getColumn(1);
        dateColumn.setMaxWidth(100);
        dateColumn.setMinWidth(100);
        dateColumn.setHeaderValue("Дата");
        dateColumn.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                if (!(value instanceof Date)) {
                    throw new IllegalArgumentException("Значение должно быть типом Date");
                }

                JTextField editor = new JTextField();
                editor.setBorder(new EmptyBorder(0, 0, 0, 0));
                editor.setText(Formatters.formatDate((Date) value));
//                editor.setBackground((row % 2 == 0) ? Color.white : Color.cyan);
                return editor;
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
//					inputElement.setText(viewTable.getSelectionModel().toString());
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

        // Create buttons panel:
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton(t.get("button.add"));
        addButton.setEnabled(false);
        addButton.setName("add");
        buttonsPanel.add(addButton);

        removeButton = new JButton(t.get("button.remove"));
        removeButton.setEnabled(false);
        removeButton.setName("del");
        buttonsPanel.add(removeButton);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void addButtonsListener(final ButtonsListener l) {

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                l.addPerformed(e);

            }
        });

        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                l.deletePerformed(e);

            }
        });
    }

    /**
     * @return the indexes of the selected rows of the list
     */
    public int[] getSelectedText() {
        return viewTable.getSelectedRows();
    }

    /**
     * @return the inputText
     */
    public String getInputText() {
        return inputElement.getText();
    }
}
