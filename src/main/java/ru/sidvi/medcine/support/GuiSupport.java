package ru.sidvi.medcine.support;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author Vitaly Sidorov
 */
public class GuiSupport {

    private GuiSupport() {
    }

    public static void registerCloseOnEsc(JRootPane root, final Window window) {
        registerListenerOnEsc(root, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ke) {
                window.dispose();
            }
        });
    }

    public static void registerListenerOnEsc(JRootPane root, final ActionListener action) {
        root.registerKeyboardAction(action,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public static void registerListenerOnCtrlEnter(JRootPane root, final ActionListener action) {
        root.registerKeyboardAction(action,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public static void registerListenerOnCtrlN(JRootPane root, final ActionListener action) {
        root.registerKeyboardAction(action,
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
