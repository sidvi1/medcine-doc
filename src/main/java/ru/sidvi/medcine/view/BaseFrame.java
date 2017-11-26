package ru.sidvi.medcine.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Vitaly Sidorov
 */
public class BaseFrame extends JFrame {

    public BaseFrame() throws HeadlessException {
        final JFrame that = this;

        // Выход по ESC
        getRootPane()
                .registerKeyboardAction(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent ke) {
                                                that.dispose();
                                            }
                                        },
                        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                        JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
