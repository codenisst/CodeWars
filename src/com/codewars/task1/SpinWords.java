package com.codewars.task1;

public class SpinWords {

    public String spinWords(String sentence) {
        if (sentence.contains(" ")) {
            String[] tmp = sentence.split(" ");
            StringBuilder result = new StringBuilder();
            int index = 0;
            for (String str : tmp) {
                if (str.length() >= 5) {
                    StringBuilder builder = new StringBuilder(str);
                    builder.reverse();
                    tmp[index] = builder.toString();
                }
                index++;
            }
            for (String str : tmp) {
                result.append(str).append(" ");
            }
            return result.toString().trim();
        }

        if (sentence.length() > 5) {
            StringBuilder builder = new StringBuilder(sentence);
            builder.reverse();
            return builder.toString();
        }

        return sentence;
    }
}

