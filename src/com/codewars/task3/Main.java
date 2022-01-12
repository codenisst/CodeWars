package com.codewars.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

    // main не участвует в тестировании.
    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("abba"));
//        System.out.println(sherlockAndAnagrams("abcd"));
//        System.out.println(sherlockAndAnagrams("cdcd"));
//        System.out.println(sherlockAndAnagrams("kkkk"));
//        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
    }

    public static int sherlockAndAnagrams(String s) {

        // Возвращаемый результат (количество неупорядоченных анаграммных пар подстрок).
        int count = 0;

        // Узнаем, присутствуют ли в строке анаграммные подстроки.
        HashSet<Character> charSetS = new HashSet<>();
        char[] charsA = s.toCharArray();
        for (char c : charsA) {
            charSetS.add(c);
        }

        // В случае отстутсвия анаграммных подстрок метод возвращает 0.
        if (charsA.length == charSetS.size()) {
            return 0;
        }

        // Объявляем список игнорируемых подстрок, дабы не учитывать повторные соответствия
        List<String> ignoredSubstrings = new ArrayList<>();

        // Формируем первую подстроку.
        for (int i = 0; i < s.length(); i++) {

            for (int j = i + 1; j < s.length(); j++) {

                List<Character> listCharsFromFirstSubstring = new ArrayList<>();
                String firstSubstring = s.substring(i, j);

                char[] charsFromFirstSubstring = firstSubstring.toCharArray();
                for (char c : charsFromFirstSubstring) {
                    listCharsFromFirstSubstring.add(c);
                }

                // Фиксируем индексы, из которых сформирована первая подстрока.
                String indexesFirstSubstring = i + " " + j;

                // Временный счетчик соответствий.
                int tmpCount = 0;

                // Формируем вторую подстроку.
                for (int k = 0; k < s.length() - listCharsFromFirstSubstring.size() + 1; k++) {
                    // Проверяем, начинается ли вторая подстрока с того же индекса строки, что и первая подстрока.
                    // Если начинается, то формируем вторую подстроку со следующего индекса, поскольку иначе обе подстроки идентичны.
                    if (k != i) {

                        String potentialAnagramm = s.substring(k, listCharsFromFirstSubstring.size() + k);

                        char[] charsPotentialAnagramm = potentialAnagramm.toCharArray();
                        List<Character> listCharsFromSecondSubstring = new ArrayList<>();
                        for (char c : charsPotentialAnagramm) {
                            listCharsFromSecondSubstring.add(c);
                        }

                        // Фиксируем индексы, из которых сформирована вторая подстрока.
                        String indexesSecondSubstring = k + " " + (listCharsFromFirstSubstring.size() + k);

                        // Узнаем, участвовала ли вторая подстрока в положительных проверках на соответствие.
                        // Если да, то формируем новую вторую подстроку.
                        if (ignoredSubstrings.contains(indexesSecondSubstring)) {
                            continue;
                        }

                        // Перед проверкой на соответствие сортируем символы в обоих подстроках.
                        List<Character> sortedCharsFromFirstSubstring = new ArrayList<>(listCharsFromFirstSubstring);
                        Collections.sort(sortedCharsFromFirstSubstring);
                        List<Character> sortedCharsFromSecondSubstring = new ArrayList<>(listCharsFromSecondSubstring);
                        Collections.sort(sortedCharsFromSecondSubstring);

                        // Проводим проверку на соответствие.
                        if (sortedCharsFromFirstSubstring.equals(sortedCharsFromSecondSubstring)) {
                            tmpCount++;
                            // Добавляем подстроку, положительно прошедшую через проверку в список игнорируемых, дабы избежать
                            // повторного учета новых анаграмм с этой подстрокой.
                            ignoredSubstrings.add(indexesFirstSubstring);
                        }
                    }
                }
                count += tmpCount;
            }
        }
        return count;
    }
}