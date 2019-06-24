package com.example.getrequestexample.utils;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.logging.Logger;

public class WorkWithTime {


    static Logger log;
    //Определяет попадает ли setTime между openTime и closeTime
    public static boolean getTrueIfSetTimeIsBetween(@NonNull Calendar setTime, @NonNull Calendar openTime,
                                                    @NonNull Calendar closeTime )
            {
        Calendar openD = Calendar.getInstance();
        Calendar closeD = Calendar.getInstance();

        int OD=openTime.get(Calendar.HOUR_OF_DAY);
        int CD=closeTime.get(Calendar.HOUR_OF_DAY);
        int diffD=CD-OD;
        if(diffD<0)
            diffD=24+diffD;
         openD.set(Calendar.HOUR_OF_DAY,OD);
         closeD.set(Calendar.HOUR_OF_DAY,OD);
         closeD.add(Calendar.HOUR_OF_DAY,diffD);
        if(setTime.before(closeD) && setTime.after(openD))
            return true;
        else
            return false;
    }
}
