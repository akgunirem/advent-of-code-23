package day03;

import util.File;
import java.util.ArrayList;

public class Day03Part1 {

    public static void main(String[] args) {
        int sum = 0;

        ArrayList<String> lines = File.readLines("input03.txt");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String prevLine = (i > 0) ? lines.get(i - 1) : null;
            String nextLine = (i < lines.size() - 1) ? lines.get(i + 1) : null;
            ArrayList<MyNumber> partNumbers = getPartNumbers(line, prevLine, nextLine);
            for (MyNumber partNumber : partNumbers) {
                sum += Integer.parseInt(partNumber.getData());
            }
        }
        System.out.println(sum); // 540131
    }

    public static ArrayList<MyNumber> getPartNumbers(String line, String prev, String next) {
        ArrayList<MyNumber> numbers = new ArrayList<>();
        ArrayList<MyNumber> partNumbers = new ArrayList<>();
        String num = "";
        MyNumber number = new MyNumber();

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                num += line.charAt(i);
                if (number.getStartIndex() == -1) {
                    number.setStartIndex(i);
                }
                if (i == line.length() - 1) {
                    number.setData(num);
                    number.setEndIndex(i - 1);
                    num = "";
                    numbers.add(number);
                    number = new MyNumber();
                }
            } else {
                if (num.length() > 0) {
                    number.setData(num);
                    number.setEndIndex(i - 1);
                    num = "";
                    numbers.add(number);
                    number = new MyNumber();
                }
            }
        }

        for (MyNumber currentNumber : numbers) {
            if ((currentNumber.getStartIndex() > 0 && isSymbol(line.charAt(currentNumber.getStartIndex() - 1)))
                    || (line.length() - 1 > currentNumber.getEndIndex() && isSymbol(line.charAt(currentNumber.getEndIndex() + 1)))) {
                partNumbers.add(currentNumber);
            } else if (currentNumber.getStartIndex() - 1 >= 0 && line.length() - 1 >= currentNumber.getEndIndex() + 1) {
                for (int j = currentNumber.getStartIndex() - 1; j <= currentNumber.getEndIndex() + 1; j++) {
                    if ((prev != null && isSymbol(prev.charAt(j))) || (next != null && isSymbol(next.charAt(j)))) {
                        partNumbers.add(currentNumber);
                        break;
                    }
                }
            } else if (currentNumber.getStartIndex() - 1 >= 0) {
                if ((prev != null && isSymbol(prev.charAt(currentNumber.getStartIndex() - 1))) || (next != null && isSymbol(next.charAt(currentNumber.getStartIndex() - 1)))) {
                    partNumbers.add(currentNumber);
                }
            } else if (line.length() - 1 >= currentNumber.getEndIndex() + 1) {
                if ((prev != null && isSymbol(prev.charAt(currentNumber.getEndIndex() + 1))) || (next != null && isSymbol(next.charAt(currentNumber.getEndIndex() + 1)))) {
                    partNumbers.add(currentNumber);
                }
            } else {
                break;
            }
        }

        return partNumbers;
    }

    public static boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }

    public static boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
