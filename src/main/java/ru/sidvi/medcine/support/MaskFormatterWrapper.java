package ru.sidvi.medcine.support;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * @author Vitaly Sidorov
 */
public class MaskFormatterWrapper {

    private MaskFormatterWrapper() {
    }


    public static MaskFormatter create(String mask) {
        try {
            return new MaskFormatter(mask);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Не валидный формат маски", e);
        }
    }
}
