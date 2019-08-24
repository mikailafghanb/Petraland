package com.rahmanarifofficial.pertaland.util;

import java.text.NumberFormat;

public class Formatter {
    public static String areaFormatter(double luas){
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        format.setGroupingUsed(true);
        return format.format(luas) + " M2";
    }
}
