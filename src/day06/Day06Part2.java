package day06;

import util.File;

public class Day06Part2 {

    public static void main(String[] args) {
        int answer = 1;

        String time = File.readLines("input06.txt").get(0).split(":")[1].replaceAll(" ", "");
        String distance = File.readLines("input06.txt").get(1).split(":")[1].replaceAll(" ", "");
        answer *= getNumberOfWays(Long.parseLong(time), Long.parseLong(distance));

        System.out.println(answer); // 26499773
    }

    private static int getNumberOfWays(long raceTime, long raceDistance) {
        int count = 0;
        long time = 0L;
        long distance = 0L;

        while (time < raceTime) {
            distance = time * (raceTime - time);
            if (distance > raceDistance) {
                count++;
            }
            time++;
        }
        return count;
    }
}
