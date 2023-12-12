package day08;

import java.util.ArrayList;
import util.File;

public class Day08Part2 {

	public static void main(String[] args) {
		ArrayList<NodePath> start = new ArrayList<NodePath>();
		ArrayList<NodePath> nodeList = new ArrayList<NodePath>();
		String instruction = "";
        for (String line : File.readLines("input08.txt")) {
            if (!line.contains("=") && !line.isEmpty()) {
				instruction=line;
			} else if (!line.isEmpty()){
				NodePath node = new NodePath();
				node.element=line.split("=")[0].trim();
				node.leftElement=line.split("=")[1].split(",")[0].replace("(","").trim();
				node.rightElement=line.split("=")[1].split(",")[1].replace(")","").trim();
				nodeList.add(node);
				if (node.element.charAt(2)=='A') {
					start.add(node);
				}
			}
        }       
        long[] countList = new long[start.size()];
        for (int i = 0; i < start.size(); i++) {
        	countList[i]=getStepCount(nodeList, start.get(i), instruction);
        }
        System.out.println(findLCM(countList)); // 18625484023687
	}
	
	static long getStepCount (ArrayList<NodePath> nodeList, NodePath start, String instruction) {
		long count=0L;
		NodePath current = start;
		while (true) {
			for (int i = 0; i < instruction.length(); i++) {
	        	if (current.element.charAt(2)=='Z') {
	        		return count;
	        	} else if (instruction.charAt(i)=='L') {
	        		for (NodePath node : nodeList) {
						if (node.element.equals(current.leftElement)) {
							current=node;
							count++;
							break;
						}
					}
	        	} else if (instruction.charAt(i)=='R') {
	        		for (NodePath node : nodeList) {
						if (node.element.equals(current.rightElement)) {
							current=node;
							count++;
							break;
						}
					}
	        	}
			}
		}
	}
	
	static long findLCM(long a, long b) {
        long max = Math.max(a, b); 
        long min = Math.min(a, b); 
        for (int i = 0; i < min; i++) {
            long lcm = max * (i+1); 
            if (lcm % min == 0) {
                return lcm;
            }
        }
        return max * min;
    }

    static long findLCM(long[] countList) {
        long lcm = 1L;
        for (long count : countList) {
            lcm = findLCM(lcm, count);
        }
        return lcm;
    }

}