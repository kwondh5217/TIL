import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int employeeCount = schedules.length;
        int result = 0;

        for (int i = 0; i < employeeCount; i++) {
            int workDaysOnTime = 0;
            int currentDay = startday;

            for (int j = 0; j < 7; j++) {
                if (currentDay > 7) currentDay = 1;

                if (currentDay >= 1 && currentDay <= 5) {
                    int targetTime = getTime(schedules[i]);
                    int actualTime = timelogs[i][j];

                    if (actualTime <= targetTime) {
                        workDaysOnTime++;
                    }
                }
                currentDay++;
            }

            if (workDaysOnTime == 5) {
                result++;
            }
        }

        return result;
    }

    private static int getTime(int time) {
        int minutes = (time % 100) + 10;
        int hours = time / 100 * 100;

        if (minutes >= 60) {
            minutes -= 60;
            hours += 100;
        }

        return hours + minutes;
    }
}
