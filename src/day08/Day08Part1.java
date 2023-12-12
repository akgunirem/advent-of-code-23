package day08;

import java.util.ArrayList;
import util.File;

public class Day08Part1 {

	public static void main(String[] args) {
		NodePath start=null;
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
				if (node.element.equals("AAA")) {
					start=node;
				}
			}
        }        
		System.out.println(getStepCount(nodeList, start, instruction)); // 17287
	}
	
	static long getStepCount (ArrayList<NodePath> nodeList, NodePath start, String instruction) {
		long count=0L;
		NodePath current = start;
		while (true) {
			for (int i = 0; i < instruction.length(); i++) {
	        	if (current.element.equals("ZZZ")) {
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
	
}
