package day01;

import util.File;
import java.util.ArrayList;

public class Day01Part2 {

    public static void main(String[] args) {
        int sum = 0;

        ArrayList<String> lines = File.readLines("input01.txt");

        for (String line : lines) {
            line = line.replace("one", "o1e")
                    .replace("two", "t2o")
                    .replace("three", "t3e")
                    .replace("four", "f4r")
                    .replace("five", "f5e")
                    .replace("six", "s6x")
                    .replace("seven", "s7n")
                    .replace("eight", "e8t")
                    .replace("nine", "n9e");

            for (int i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    int digit = line.charAt(i) - '0';
                    sum += digit;
                    break;
                }
            }
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    int digit = line.charAt(i) - '0';
                    sum += digit * 10;
                    break;
                }
            }
        }
        System.out.println(sum); // 55358
    }
}
