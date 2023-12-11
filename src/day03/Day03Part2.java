package day03;

import util.File;
import java.util.ArrayList;

public class Day03Part2 {

	public static void main(String[] args) {
		
	    int sum=0;
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
		System.out.println(sum); // ??
	}
	
	public static ArrayList<MyNumber> getPartNumbers (String line, String prev, String next) {
		ArrayList<MyNumber> numbers = new ArrayList<MyNumber>();
		ArrayList<MyNumber> partNumbers = new ArrayList<MyNumber>();
		String num = "";
		MyNumber number = new MyNumber();
		for (int i = 0; i < line.length(); i++) {
			if(Character.isDigit(line.charAt(i))){
				num+=line.charAt(i);
				if (number.getStartIndex()==-1) {
					number.setStartIndex(i);
				}
				if (i==line.length()-1) {
					number.setData(num);
					number.setEndIndex(i-1);
					num="";
					numbers.add(number);
					number = new MyNumber();
				}
			} else {
				if(num.length()>0) {
					number.setData(num);
					number.setEndIndex(i-1);
					num="";
					numbers.add(number);
					number = new MyNumber();
				}
			}
		}
		for (int i = 0; i < numbers.size(); i++) {
			if ((numbers.get(i).getStartIndex()>0 && isStar(line.charAt(numbers.get(i).getStartIndex()-1)))
					|| (line.length()-1>numbers.get(i).getEndIndex() && isStar(line.charAt(numbers.get(i).getEndIndex()+1)))) {
				partNumbers.add(numbers.get(i));
			} else if (numbers.get(i).getStartIndex()-1>=0 && line.length()-1>=numbers.get(i).getEndIndex()+1) {
				for (int j = numbers.get(i).getStartIndex()-1; j <= numbers.get(i).getEndIndex()+1; j++) {
					if ((prev!=null && isStar(prev.charAt(j))) || (next!=null && isStar(next.charAt(j)))) {
						partNumbers.add(numbers.get(i));
						break;
					}
				}
			} else if(numbers.get(i).getStartIndex()-1>=0) {
				if((prev!=null && isStar(prev.charAt(numbers.get(i).getStartIndex()-1))) || (next!=null&&isStar(next.charAt(numbers.get(i).getStartIndex()-1)))) {
					partNumbers.add(numbers.get(i));
				}
			} else if(line.length()-1>=numbers.get(i).getEndIndex()+1) {
				if((prev!=null && isStar(prev.charAt(numbers.get(i).getEndIndex()+1))) || (next!=null&&isStar(next.charAt(numbers.get(i).getEndIndex()+1)))) {
					partNumbers.add(numbers.get(i));
				}
			} else { 
				break; 
			}
		}
		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i).getData() + " " + numbers.get(i).getStartIndex() + " " + numbers.get(i).getEndIndex());
		}
		return partNumbers;
	}
	
	public static boolean isStar(char c) {
		return c == '*';
	}
	
	public static boolean containsDigit(String s) {
		for (int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
