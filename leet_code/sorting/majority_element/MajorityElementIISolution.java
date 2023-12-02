import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MajorityElementIISolution {

    class Solution {

        private int majorityTarget;
        private HashMap<Integer, Integer> occurencesMap;
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> occurencesAboveMajorityTarget = new ArrayList<>();
            occurencesMap = new HashMap<>();
            majorityTarget = nums.length /3;

            for (int i = 0; i < nums.length; i++) {
                addValueToMap(nums[i]);
            }

            int max = Collections.max(occurencesMap.values());
            if (max > majorityTarget) {
                for (int key : occurencesMap.keySet()) {
                    if (occurencesMap.get(key) > majorityTarget) {
                        occurencesAboveMajorityTarget.add(key);
                    }
                }
            }
            return occurencesAboveMajorityTarget;
        }

        private void addValueToMap(int value) {
            if (occurencesMap.containsKey(value)) {
                occurencesMap.put(value, occurencesMap.get(value) + 1);
            } else {
                occurencesMap.put(value, 1);
            }
        }
    }
}
