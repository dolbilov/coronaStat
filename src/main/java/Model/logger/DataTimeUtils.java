package Model.logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for get current time and data
 * @author Kirill Dolbilov
 * @version 1.0
 */
public class DataTimeUtils {
    /** pattern for current date time: <i>dd.mm.yyyy hh:mm:ss</i> */
    private static final String dateTimePattern = "dd/MM/yyyy kk:mm:ss";

    /** Hidden constructor to deny creating object */
    private DataTimeUtils() {}

    /**
     * Get string with current date and time in format <i>dd.mm.yyyy hh:mm:ss</i>
     * @return string with current date and time
     */
    public static String GetCurrentDateTime()
    {
        var currentDT = LocalDateTime.now();
        var dtf = DateTimeFormatter.ofPattern(dateTimePattern);
        return dtf.format(currentDT);
    }
}
