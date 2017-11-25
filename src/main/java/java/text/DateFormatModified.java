package java.text;

import java.util.Date;

/**
 * @author Vitaly Sidorov
 */
public class DateFormatModified extends SimpleDateFormat {

    public DateFormatModified(String patter) {
        super(patter);
    }

    @Override
    public Date parse(String source) {
        ParsePosition pos = new ParsePosition(0);
        Date result = parse(source, pos);
        if (pos.index == 0)
            throw new RuntimeException("Unparseable date: \"" + source + "\" at" + pos.errorIndex);
        return result;
    }
}
