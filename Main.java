/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dheerajp316
 */
/*Sample code to read in test cases:*/
public class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            String[] words;
            while ((line = in.readLine()) != null) {
                if (line.length() > 0) {
                    System.out.println(getShortestRepetition(line));
                }
            }

        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static int getShortestRepetition(String string) {
        int count=0;
        int reps=1;
        String temp;
        for(int i=1; i<=string.length(); i++){
            count = 0;
            temp = string.substring(0,i);
            Pattern regex =  Pattern.compile(temp);
            Matcher matcher = regex.matcher(string);
            while(matcher.find()) {
                count++;
            }
            if(count*temp.length() == string.length()) {
                reps = i;
                break;
            }
        }
        return reps;
    }
    
    public static void getMaxChainWords(String[] array) {
        char[] start = new char[array.length];
        char[] end = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            start[i] = array[i].charAt(0);
            end[i] = array[i].charAt(array[i].length() - 1);
        }
        
        java.util.Arrays.sort(start);
        java.util.Arrays.sort(end);
        
        int intersection = 0;
        int startCount = 0, endCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (start[startCount] > end[endCount]) {
                endCount++;
            } else if (start[startCount] < end[endCount]) {
                startCount++;
            } else if(start[startCount] == end[endCount]) {
                intersection++;
                startCount++;
                endCount++;
            }
        }
        if (intersection == 0) {
            System.out.println("None");
        } else if (intersection == array.length) {
            System.out.println(intersection);
        } else {
            System.out.println(intersection+1);
        }
    }

    public static char get1stNonRepeatedCharacter(String string) {
        Map<Character, Integer> charMap = new HashMap<>();
        int count;

        for (int i = 0; i < string.length(); i++) {
            count = 0;
            if (!charMap.containsKey(string.charAt(i))) {
                charMap.put(string.charAt(i), 1);
            } else {
                count = charMap.get(string.charAt(i));
                count++;
                charMap.put(string.charAt(i), count);
            }
        }

        for (int i = 0; i < string.length(); i++) {
            if (charMap.get(string.charAt(i)) == 1) {
                return string.charAt(i);
            }
        }
        return ' ';
    }

    public static String getMthToLastElement(String[] array) {

        int fastPointer = Integer.parseInt(array[array.length - 1]), slowPointer = 0;
//        if(fastPointer == 0) {
//            return array[array.length - 2];
//        }
        if (fastPointer > array.length - 1) {
            return "";
        }
        while (fastPointer <= array.length - 2) {
            slowPointer++;
            fastPointer++;
        }
        return array[slowPointer];
    }

    public static int getMajorityElement(String[] array) {
        int majIndex = 0, count = 0;
        for (int i = 0; i < array.length; i++) {
            if (Integer.parseInt(array[majIndex]) == Integer.parseInt(array[i])) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majIndex = i;
                count = 1;
            }
        }
        return Integer.parseInt(array[majIndex]);
    }

    public static boolean isMajorityElement(int element, String[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (element == Integer.parseInt(array[i])) {
                count++;
            }
        }
        if (count > array.length / 2) {
            return true;
        }
        return false;
    }

    public static void getSetsIntersection(String[] set1, String[] set2) {
        int i = 0, j = 0;
        int set1len = set1.length;
        int set2len = set2.length;
        int count = 0;
        int[] intersection;
        if (set1len <= set2len) {
            intersection = new int[set1len];
        } else {
            intersection = new int[set2len];
        }
        int num1, num2;
        while (i < set1.length && j < set2.length) {
            num1 = Integer.parseInt(set1[i]);
            num2 = Integer.parseInt(set2[j]);
            if (num1 < num2) {
                i++;
            } else if (num1 > num2) {
                j++;
            } else {
                intersection[count] = num1;
                count++;
                i++;
                j++;
            }
        }
        if (count == 0) {
            System.out.println("\n");
        } else {
            for (i = 0; i < count; i++) {
                System.out.print(intersection[i]);
                if (i != count - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("");
        }
    }

    public static void getNLargestSentences(String filename) {
        File file = new File(filename);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            int count = 0;
            List<String> nStrings = new ArrayList<>();
            line = in.readLine();
            int n = Integer.parseInt(line);
            StringBuilder sb;
            Queue<String> stringQueue = new PriorityQueue<>(n, comparator);
            while ((line = in.readLine()) != null) {
                if (line.length() > 0) {
                    if (stringQueue.isEmpty() || count < n) {
                        stringQueue.add(line);
                        count++;
                    } else {
                        sb = new StringBuilder(stringQueue.peek());
                        if (line.length() > sb.length()) {
                            stringQueue.poll();
                            stringQueue.add(line);
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                nStrings.add(0, stringQueue.poll());
            }
            for (String string : nStrings) {
                System.out.println(string);
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    public static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return (s1.length() - s2.length());
        }
    };

    public static void fizzBuzz(int fizz, int buzz, int limit) {
        for (int i = 1; i <= limit; i++) {
            if (i % fizz == 0 && i % buzz == 0) {
                System.out.print("FB ");
            } else if (i % buzz == 0) {
                System.out.print("B ");
            } else if (i % fizz == 0) {
                System.out.print("F ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    private static class WordIndex {

        private String word;
        private int index;

        public WordIndex(String word, int index) {
            this.word = word;
            this.index = index;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public static String getRecoveredString(String[] wordArray, String[] inputArray) {
        StringBuilder sentence = new StringBuilder();
        List<WordIndex> wordIndexes = new ArrayList<>();
        int sum = 0;
        int count = 0;
        for (int i = 0; i <= wordArray.length - 2; i++) {
            wordIndexes.add(new WordIndex(wordArray[i], Integer.parseInt(inputArray[count])));
            sum += Integer.parseInt(inputArray[count]);
            count++;
        }
        // need to find the missing integer for the last word
        int numberOfWords = wordArray.length;
        int sumOfFirstN = numberOfWords * (numberOfWords + 1) / 2;
        int missingIndex = sumOfFirstN - sum;
        wordIndexes.add(new WordIndex(wordArray[wordArray.length - 1], missingIndex));
        Collections.sort(wordIndexes, new Comparator<WordIndex>() {
            @Override
            public int compare(WordIndex o1, WordIndex o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });

        for (WordIndex word : wordIndexes) {
            sentence.append(word.getWord()).append(" ");
        }
        return sentence.toString();
    }

    public static int getMaxBeautyOfTheString(String sentence1) {
        int maxBeauty = 0;
        int[] charBeauty = new int[26];
        for (int i = 1; i <= 26; i++) {
            charBeauty[i - 1] = i;
        }
        String sentence = sentence1.toLowerCase();
        Map<Character, Integer> beautyMap = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            if ((sentence.charAt(i) > 64 && sentence.charAt(i) < 91) || (sentence.charAt(i) > 96 && sentence.charAt(i) < 123)) {
                if (!beautyMap.containsKey(sentence.charAt(i))) {
                    beautyMap.put(sentence.charAt(i), 1);
                } else {
                    int count = beautyMap.get(sentence.charAt(i));
                    count++;
                    beautyMap.put(sentence.charAt(i), count);
                }
            } else {
                continue;
            }
        }
        Collection<Integer> beautyValues = beautyMap.values();
        List<Integer> beautyValueList = new ArrayList<>(beautyValues);
        Collections.sort(beautyValueList);
        Collections.reverse(beautyValueList);
        int counter = 25;
        for (int beauty : beautyValueList) {
            maxBeauty += beauty * charBeauty[counter];
            counter--;
        }
        return maxBeauty;
    }

    public static int getSumOfDigits(BigInteger number) {
        int sum = 0;
        BigInteger ten = BigInteger.TEN;
        BigInteger mod = BigInteger.ZERO;
        while (number.compareTo(BigInteger.ZERO) > 0) {
            mod = number.mod(ten);
            number = number.divide(ten);
            sum += mod.intValue();
        }
        return sum;
    }

    public static String reverseWord(String word) {
        int i = word.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            sb.append(word.charAt(i));
            i--;
        }

        return sb.toString();
    }

    public static String reverseSentence(String sentence) {

        int i = 0;
        int wordStart = 0;
        StringBuilder sb = new StringBuilder();
        while (i < sentence.length()) {
            if (sentence.charAt(i) == ' ') {
                sb.append(reverseWord(sentence.substring(wordStart, i))).append(" ");
                i++;
                wordStart = i;

            } else if (i == sentence.length() - 1) {
                sb.append(reverseWord(sentence.substring(wordStart, i + 1)));
                i++;
            } else {
                i++;
                continue;
            }
        }
        return reverseWord(sb.toString());
    }

    public static BigInteger getNthFibonacciNumber(int n) {
        if (n == 1 || n == 0) {
            return BigInteger.ONE;
        }
        //long[] fibSeq = new long[n];
        List<BigInteger> fibSeq = new ArrayList<>();

        fibSeq.add(BigInteger.ONE);
        fibSeq.add(BigInteger.ONE);

        for (int i = 2; i < n; i++) {
            fibSeq.add(fibSeq.get(i - 1).add(fibSeq.get(i - 2)));
        }

        return fibSeq.get(n - 1);
    }
}
