package day06;

import util.File;

public class Day06Part1 {

    public static void main(String[] args) {
        int answer = 1;

        String[] time = File.readLines("input06.txt").get(0).split(":")[1].trim().split("\\s+");
        String[] distance = File.readLines("input06.txt").get(1).split(":")[1].trim().split("\\s+");
        int[][] tableRace = new int[time.length][2];

        for (int i = 0; i < time.length; i++) {
            tableRace[i][0] = Integer.parseInt(time[i]);
            tableRace[i][1] = Integer.parseInt(distance[i]);
            answer *= getNumberOfWays(tableRace[i]);
        }
        System.out.println(answer); // 1660968
    }

    private static int getNumberOfWays(int[] tableRace) {
        int count = 0, time = 0, distance = 0;

        while (time < tableRace[0]) {
            distance = time * (tableRace[0] - time);
            if (distance > tableRace[1]) {
                count++;
            }
            time++;
        }
        return count;
    }
}
