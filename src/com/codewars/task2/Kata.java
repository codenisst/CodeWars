package com.codewars.task2;

public class Kata {

    public static String getMiddle(String str) {
        StringBuilder builder = new StringBuilder();
        if (str.length() % 2 == 0) {
            builder.append(str.charAt(str.length()/2-1)).append(str.charAt((str.length()/2)));
        } else {
            builder.append(str.charAt((str.length()/2)));
        }
        return builder.toString();
    }
}
