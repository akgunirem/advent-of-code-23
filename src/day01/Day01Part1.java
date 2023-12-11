package day01;

import java.util.ArrayList;
import util.File;

public class Day01Part1 {

    public static void main(String[] args) {
        int sum = 0;

        ArrayList<String> lines = File.readLines("input01.txt");

        for (String line : lines) {
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

        System.out.println(sum); // 56042
    }
}
