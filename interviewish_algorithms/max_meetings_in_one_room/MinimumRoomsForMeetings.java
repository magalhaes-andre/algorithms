import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumRoomsForMeetings {

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{1, 4}, {2, 4}, {5, 6}, {7, 10}, {8,11}};

        System.out.println(minimumMeetingRooms(intervals));
        System.out.println(minimumMeetingRooms(intervals2));
    }

    public static int minimumMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (queue.peek() <= currentInterval[0]) {
                queue.poll();
            }
            queue.add(currentInterval[1]);
        }
        return queue.size();
    }
}
