package com.ws.taskmanager.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatter {
    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }
}
