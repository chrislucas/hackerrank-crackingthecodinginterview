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
    public static final String DEFAUL_BR_FORMAT_DATE_3    = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAUL_BR_FORMAT_DATE_4    = "yyyy-MM-dd'T'hh:mm:ss";
    public static final String DEFAUL_BR_FORMAT_DATE_5    = "yyyy-MM-dd'T'HH:mm:ss.S";

    public static long convertFormatDateToMilliseconds(String fmt, String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, Locale.getDefault());
        //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        long milliseconds = -1;
        try {
            Date date = dateFormat.parse(strDate);
            return date.getTime();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return milliseconds;
    }


    public static String convertLongToDateFormat(long date, String formatDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
        String fmt = dateFormat.format(new Date(date));
        return fmt;
    }


    public static String convertLongToDateFormat(long date) {
        return convertLongToDateFormat(date, DEFAUL_BR_FORMAT_DATE_2);
    }

    public static void test1() {
        long millis = Calendar.getInstance().getTimeInMillis();
        System.out.println(millis);
        String date = convertLongToDateFormat(millis);
        System.out.println(date);
        millis = convertFormatDateToMilliseconds("dd-MM-yyyy HH:mm:ss", date);
        System.out.println(millis);
        date = convertLongToDateFormat(millis);
        System.out.println(date);
    }

    public static void test2() {
        /*
        long millis = Calendar.getInstance().getTimeInMillis();
        System.out.println(millis);
        String date = convertLongToDateFormat(millis, DEFAUL_BR_FORMAT_DATE_4);
        System.out.println(date);
        */
        long millis2 = convertFormatDateToMilliseconds(DEFAUL_BR_FORMAT_DATE_5,"2017-07-20T13:09:30.793");
        System.out.println(millis2);
        System.out.println(convertLongToDateFormat(millis2, DEFAUL_BR_FORMAT_DATE_5));

        System.out.println("\n\n");

        millis2 = convertFormatDateToMilliseconds(DEFAUL_BR_FORMAT_DATE_5,"2017-07-19T15:51:42.753");
        System.out.println(millis2);
        System.out.println(convertLongToDateFormat(millis2, DEFAUL_BR_FORMAT_DATE_5));
    }

    public static void main(String[] args) {
        test2();
    }

}
