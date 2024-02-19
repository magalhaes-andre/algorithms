import javax.swing.*;
import java.util.HashMap;

class Solution {
    public int smallestDivisor(int[] nums, int threshold) {

        return binarySearchSolution(nums, threshold);
    }

    public int binarySearchSolution(int[] nums, int threshold) {
        int left = 1;
        int right = 1000000;

        while (left <= right) {
            int mid = left+(right-left)/2;
            int sum = divisorSum(nums, mid);
            if (sum > threshold) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int divisorSum(int[] nums, int divisor) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += Math.ceilDiv(nums[i], divisor);
        }
        return sum;
    }

    //Works but exceeds time limit on leetcode;
    public int bruteForceSolution(int[] nums, int threshold) {
        HashMap<Integer, Integer> divisorsAndSums = new HashMap<>();
        int currentDivisor = 1;
        while (true) {
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                result += Math.ceilDiv(nums[i], currentDivisor);
            }
            divisorsAndSums.put(currentDivisor, result);
            if (divisorsAndSums.get(currentDivisor) <= threshold) {
                break;
            }
            currentDivisor++;
        }

        return currentDivisor;
    }
}