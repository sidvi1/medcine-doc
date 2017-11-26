package ru.sidvi.medcine.support;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Vitaly Sidorov
 */
public class GuiSupport {

    private GuiSupport() {
    }

    public static void registerCloseOnEsc(JRootPane root, final Window window) {
        root.registerKeyboardAction(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent ke) {
                                            window.dispose();
                                        }
                                    },
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
