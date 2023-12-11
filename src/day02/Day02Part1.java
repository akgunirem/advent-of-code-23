package day02;

import util.File;

public class Day02Part1 {

    public static void main(String[] args) {
        int sum = 0;
        for (String line : File.readLines("input02.txt")) {
            line = line.replace(" ", "");
            line = line.replace("Game", "");
            String[] splitLine = line.split(":");
            if (isPossible(splitLine[1])) {
                sum += Integer.parseInt(splitLine[0]);
            }
        }
        System.out.println(sum); // 1734
    }

    public static boolean isPossible(String line) {
        int red = 12;
        int green = 13;
        int blue = 14;
        String[] set = line.split(";");
        for (String cubeSet : set) {
            if (cubeSet.contains(",")) {
                String[] cubes = cubeSet.split(",");
                for (String cube : cubes) {
                    if (cube.contains("red") && (Integer.parseInt(cube.split("red")[0]) > red)) {
                        return false;
                    }
                    if (cube.contains("green") && (Integer.parseInt(cube.split("green")[0]) > green)) {
                        return false;
                    }
                    if (cube.contains("blue") && (Integer.parseInt(cube.split("blue")[0]) > blue)) {
                        return false;
                    }
                }
            } else {
                if (cubeSet.contains("red") && (Integer.parseInt(cubeSet.split("red")[0]) > red)) {
                    return false;
                }
                if (cubeSet.contains("green") && (Integer.parseInt(cubeSet.split("green")[0]) > green)) {
                    return false;
                }
                if (cubeSet.contains("blue") && (Integer.parseInt(cubeSet.split("blue")[0]) > blue)) {
                    return false;
                }
            }
        }
        return true;
    }
}
