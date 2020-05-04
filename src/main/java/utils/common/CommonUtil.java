package utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static String convertDateString(String source, String sourceFormat, String desFormat){
        try {
            SimpleDateFormat formatSrc = new SimpleDateFormat(sourceFormat);
            SimpleDateFormat formatDes = new SimpleDateFormat(desFormat);
            Date dateSrc = formatSrc.parse(source);
            String dateDesString = formatDes.format(dateSrc);
            return dateDesString;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
