package com.shahab.i180731_i180650;

import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class SpecificChatComparator implements Comparator<SpecificChatRVModel> {

    @Override
    public int compare(SpecificChatRVModel specificChatRVModel, SpecificChatRVModel t1) {
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        Date time1 = Calendar.getInstance().getTime();
        Date time2 = Calendar.getInstance().getTime();

        try {
            time1 = (Date)dateFormat.parse(specificChatRVModel.time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            time2 = (Date)dateFormat.parse(t1.time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time1.compareTo(time2);
    }
}
