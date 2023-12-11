package day04;

import util.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day04Part2 {

    private static HashMap<CardNode, Integer> mapCardPoints = new HashMap<>();

    public static void main(String[] args) {
        int sum = 0;

        LinkedList<CardNode> cards = new LinkedList<>();
        ArrayList<String> lines = File.readLines("input04.txt");
        CardNode prevNode = null;

        for (String line : lines) {
            CardNode currNode = new CardNode(line);
            if (prevNode != null) {
                prevNode.next = currNode;
            }
            cards.add(currNode);
            prevNode = currNode;
        }

        for (CardNode node : cards) {
            sum += getPoints(cards, node);
            sum++;
        }

        System.out.println(sum); // 6283755
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

    public static int getPoints(LinkedList<CardNode> cards, CardNode myNode) {
        if (myNode == null) {
            return 0;
        }
        if (mapCardPoints.containsKey(myNode)) {
            return mapCardPoints.get(myNode);
        }

        ArrayList<Integer> winning = getNumbers(myNode.line.split(":")[1].split("\\|")[0]);
        ArrayList<Integer> guess = getNumbers(myNode.line.split(":")[1].split("\\|")[1]);

        int count = 0;
        int point = 0;

        for (int i = 0; i < winning.size(); i++) {
            for (int j = 0; j < guess.size(); j++) {
                if (winning.get(i).equals(guess.get(j))) {
                    count++;
                }
            }
        }

        point += count;
        CardNode nextNode = myNode.next;

        for (int i = 0; i < count; i++) {
            if (nextNode != null) {
                point += getPoints(cards, nextNode);
                nextNode = nextNode.next;
            }
        }

        mapCardPoints.put(myNode, point);
        return point;
    }
}

class CardNode {
    public String line;
    public CardNode next;

    public CardNode(String line) {
        this.line = line;
    }
}
