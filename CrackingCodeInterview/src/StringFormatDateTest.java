import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by r028367 on 20/07/2017.
 */
public class StringFormatDateTest {


    public static final String DEFAUL_BR_FORMAT_DATE_2    = "dd-MM-yyyy HH:mm:ss";

    public static long convertFormatDateToMilliseconds(String fmt, String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        long milliseconds = -1;
        try {
            Date date = dateFormat.parse(strDate);
            return date.getTime();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return milliseconds;
    }


    public static String convertLongToDateFormat(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAUL_BR_FORMAT_DATE_2);
        String fmt = dateFormat.format(new Date(date));
        return fmt;
    }


    public static void main(String[] args) {

        long millis = Calendar.getInstance().getTimeInMillis();
        System.out.println(millis);
        String date = convertLongToDateFormat(millis);
        System.out.println(date);
        millis = convertFormatDateToMilliseconds("dd-MM-yyyy HH:mm:ss", date);
        System.out.println(millis);
        date = convertLongToDateFormat(millis);
        System.out.println(date);
    }

}
