package day07;

import util.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Day07Part2 {
	
	final static int FIVE_OF_A_KIND=6, FOUR_OF_A_KIND=5, FULL_HOUSE=4, THREE_OF_A_KIND=3, TWO_PAIR=2, ONE_PAIR=1, HIGH_CARD=0;
	
	public static void main(String[] args) {
		int sum=0;
		ArrayList<NodeHand> arrHands = new ArrayList<>();
        for (String line : File.readLines("input07.txt")) {
            NodeHand hand = new NodeHand();
            String cards = line.split(" ")[0];
            hand.cards = cards;
            hand.bid = Integer.parseInt(line.split(" ")[1]);
            hand.type = getType(hand);
            arrHands.add(hand);
        }
        sortHands(arrHands);
        for (int i = 0; i < arrHands.size(); i++) {
            arrHands.get(i).rank = i + 1;
            sum += (arrHands.get(i).bid * arrHands.get(i).rank);
        }
		System.out.println(sum); // 250757288
	}

	static int getType(NodeHand hand) {
		String input=hand.cards;
		int count = 0;
		HashMap<Character, Integer> charCounts = new HashMap<>();
        for (char ch : input.toCharArray()) {
            charCounts.put(ch, charCounts.getOrDefault(ch, 0) + 1);
        }
	    for (HashMap.Entry<Character, Integer> entry : charCounts.entrySet()) {
				if(entry.getValue()>count && !entry.getKey().equals('J')) {
					count=entry.getValue();
				} 
			}
        if(charCounts.get('J')!=null && count!=5) {
            count+=charCounts.get('J');        	
        }
        charCounts.remove('J');
        
        switch (count) {
		case 5:
			return FIVE_OF_A_KIND;
		case 4:
			return FOUR_OF_A_KIND;
		case 3:
			if(charCounts.entrySet().size()==2) {
        		return FULL_HOUSE;
        	} else {
        		return THREE_OF_A_KIND;
        	}
		case 2:
			if(charCounts.entrySet().size()==3) {
        		return TWO_PAIR;
        	} else {
        		return ONE_PAIR;
        	}
		default:
			return HIGH_CARD;
		}
	}
	
	static void sortHands(ArrayList<NodeHand> arrHands) {
		HashMap<Character, Integer> cardValues = new HashMap<>();
        cardValues.put('A', 14);
        cardValues.put('K', 13);
        cardValues.put('Q', 12);
        cardValues.put('T', 11);
        cardValues.put('9', 10);
        cardValues.put('8', 9);
        cardValues.put('7', 8);
        cardValues.put('6', 7);
        cardValues.put('5', 6);
        cardValues.put('4', 5);
        cardValues.put('3', 4);
        cardValues.put('2', 3);
        cardValues.put('J', 2);
		for (int i = 0; i < arrHands.size(); i++) {
			for (int j = i; j < arrHands.size(); j++) {
				if (arrHands.get(i).type>arrHands.get(j).type) {
                    swap(arrHands, i, j);	
				}	else if (arrHands.get(i).type==arrHands.get(j).type) {
					for (int k = 0; k < 5; k++) {
						if (cardValues.get(arrHands.get(i).cards.charAt(k))>cardValues.get(arrHands.get(j).cards.charAt(k))) {
							swap(arrHands, i, j);	
							break;
						} else if (cardValues.get(arrHands.get(i).cards.charAt(k))<cardValues.get(arrHands.get(j).cards.charAt(k))) {
	                        break;
	                    }
					} 
				}
			}
		}
	}
	
	static void swap(ArrayList<NodeHand> arrHands, int i, int j) {
        NodeHand temp = arrHands.get(i);
        arrHands.set(i, arrHands.get(j));
        arrHands.set(j, temp);
    }
	
}