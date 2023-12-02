//        Given an array nums of size n, return the majority element.
//
//        The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//
//
//
//        Example 1:
//
//        Input: nums = [3,2,3]
//        Output: 3
//        Example 2:
//
//        Input: nums = [2,2,1,1,1,2,2]
//        Output: 2
//
//
//        Constraints:
//
//        n == nums.length
//        1 <= n <= 5 * 104
//        -109 <= nums[i] <= 109
//
//
//        Follow-up: Could you solve the problem in linear time and in O(1) space?

import java.util.Collections;
import java.util.HashMap;

public class MajorityElementSolution {

    class Solution {
        private int majorityTarget;
        private HashMap<Integer, Integer> occurencesMap = new HashMap<>();

        public int majorityElement(int[] nums) {
            majorityTarget = (nums.length - 1) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (occurencesMap.containsKey(nums[i])) {
                    occurencesMap.put(nums[i], occurencesMap.get(nums[i]) + 1);
                } else {
                    occurencesMap.put(nums[i], 1);
                }
            }
            int max = Collections.max(occurencesMap.values());
            if (max > majorityTarget) {
                for (int key : occurencesMap.keySet()) {
                    if (occurencesMap.get(key) == max) {
                        return key;
                    }
                }
            }
            return 0;
        }
    }
}
