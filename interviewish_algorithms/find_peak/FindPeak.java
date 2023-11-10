import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FindPeak {

    public static void main(String[] args) {
        int[] numbers = {1, 5, 2, 20, 3, 14, 55, 60, 90, 100, 12, 34, 15, 24, 72, 46, 88, 97, 112, 234, 543, 232};
        long start;
        long end;
        start = System.currentTimeMillis();
        System.out.println("Highest Peak: " + findPeak(numbers));
        end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start));
        start = System.currentTimeMillis();
        System.out.println("First Peak: " + findFirstPeak(numbers));
        end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start));
        start = System.currentTimeMillis();
        System.out.println("Divide and Conquer Peak: " + findPeakDivideAndConquer(numbers));
        end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start));
    }

    public static int findPeak(int[] numbers) {
        int peak = 0;
        int majorPeak = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (i == 0 && numbers[i + 1] < numbers[i]) {
                peak = numbers[i];
            } else if (i == numbers.length - 1 && numbers[i - 1] < numbers[i]) {
                peak = numbers[i];
            } else if (i > 0 && numbers[i] > numbers[i - 1] && numbers[i] > numbers[i + 1]) {
                peak = numbers[i];
            } else if (peak > majorPeak) {
                majorPeak = peak;
            }
        }

        return majorPeak;
    }

    public static int findFirstPeak(int[] numbers) {
        int peak = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (i == 0 && numbers[i + 1] < numbers[i]) {
                peak = numbers[i];
                break;
            } else if (i == numbers.length - 1 && numbers[i - 1] < numbers[i]) {
                peak = numbers[i];
                break;
            } else if (i > 0 && numbers[i] > numbers[i - 1] && numbers[i] > numbers[i + 1]) {
                peak = numbers[i];
                break;
            }
        }

        return peak;
    }

    private static int findPeakDivideAndConquer(int[] numbers) {
        int middle = numbers.length % 2 == 0 ? numbers.length / 2 : (numbers.length + 1) / 2;
        int peak = 0;
        for (int i = 1; i < numbers.length; i++) {
            int left = numbers[middle - 1];
            int right = numbers[middle + 1];
            if (numbers[middle] > left && numbers[middle] > right) {
                peak = numbers[middle];
                break;
            } else {
                middle = left > right ? middle - 1 : middle + 1;
            }
        }

        return peak;
    }
}
