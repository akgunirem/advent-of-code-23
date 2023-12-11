package day02;

import util.File;

public class Day02Part2 {

    public static void main(String[] args) {
        int sum = 0;
        for (String line : File.readLines("input02.txt")) {
            line = line.replace(" ", "");
            String[] splitLine = line.split(":");
            sum += setPower(splitLine[1]);
        }
        System.out.println(sum); // 70387
    }

    public static int setPower(String line) {
        int red = 0;
        int green = 0;
        int blue = 0;
        String[] set = line.split(";");
        for (String cubeSet : set) {
            if (cubeSet.contains(",")) {
                String[] cubes = cubeSet.split(",");
                for (String cube : cubes) {
                    if (cube.contains("red") && (Integer.parseInt(cube.split("red")[0]) > red)) {
                        red = Integer.parseInt(cube.split("red")[0]);
                    }
                    if (cube.contains("green") && (Integer.parseInt(cube.split("green")[0]) > green)) {
                        green = Integer.parseInt(cube.split("green")[0]);
                    }
                    if (cube.contains("blue") && (Integer.parseInt(cube.split("blue")[0]) > blue)) {
                        blue = Integer.parseInt(cube.split("blue")[0]);
                    }
                }
            } else {
                if (cubeSet.contains("red") && (Integer.parseInt(cubeSet.split("red")[0]) > red)) {
                    red = Integer.parseInt(cubeSet.split("red")[0]);
                }
                if (cubeSet.contains("green") && (Integer.parseInt(cubeSet.split("green")[0]) > green)) {
                    green = Integer.parseInt(cubeSet.split("green")[0]);
                }
                if (cubeSet.contains("blue") && (Integer.parseInt(cubeSet.split("blue")[0]) > blue)) {
                    blue = Integer.parseInt(cubeSet.split("blue")[0]);
                }
            }
        }
        return red * blue * green;
    }
}
