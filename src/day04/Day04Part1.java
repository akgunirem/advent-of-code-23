package day04;

import util.File;

import java.util.ArrayList;

public class Day04Part1 {

    public static void main(String[] args) {
        int sum = 0;
        ArrayList<String> lines = File.readLines("input04.txt");
        for (String line : lines) {
            sum += getPoints(line);
        }
        System.out.println(sum); // 15268
    }

    public static ArrayList<Integer> getNumbers(String line) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : line.split(" ")) {
            if (!part.isEmpty()) {
                numbers.add(Integer.parseInt(part));
            }
        }
        return numbers;
    }

    private static int getPoints(String line) {
        ArrayList<Integer> winning = getNumbers(line.split(":")[1].split("\\|")[0]);
        ArrayList<Integer> guess = getNumbers(line.split(":")[1].split("\\|")[1]);
        int count = 0;

        for (int winningNumber : winning) {
            for (int guessNumber : guess) {
                if (winningNumber == guessNumber) {
                    count++;
                }
            }
        }

        return (count == 0 ? 0 : (int) Math.pow(2, count - 1));
    }
}
