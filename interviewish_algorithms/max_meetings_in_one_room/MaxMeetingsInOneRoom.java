import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMeetingsInOneRoom {

    public static void main(String[] args) {
        int numberOfMeetings = 6;
        int[] start = {1,0,3,8,5,8};
        int[] end = {2,6,4,9,7,9};
        System.out.println(comparatorSolution(numberOfMeetings, start, end).toString());
    }

    public static List<Meeting> comparatorSolution(int numberofMeetings, int[] start, int[] end) {
        ArrayList<Meeting> meetings = new ArrayList<>();
        for (int iteration = 0; iteration < numberofMeetings; iteration ++) {
            meetings.add(new Meeting(iteration + 1, start[iteration], end[iteration]));
        }
        MeetingComparator comparator = new MeetingComparator();
        meetings.sort(comparator);
        ArrayList<Meeting> availableMeetings = new ArrayList<>();
        availableMeetings.add(meetings.get(0));
        int limit = availableMeetings.get(0).getEnd();

        for (int i = 1; i < numberofMeetings; i++) {
            if (meetings.get(i).getStart() > limit){
                availableMeetings.add(meetings.get(i));
                limit = meetings.get(i).getEnd();
            }
        }

        return availableMeetings;
    }
    private static class Meeting {
        int index;
        int start;
        int end;

        public Meeting(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return index + " = " + start + "~" + end;
        }

        public int getIndex() {
            return index;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
    private static class MeetingComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            if (o1.end < o2.end) {
                return -1;
            } else if (o1.end > o2.end) {
                return 1;
            } else if (o1.index < o2.index) {
                return -1;
            }
            return 1;
        }
    }
}
