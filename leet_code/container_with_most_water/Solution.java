//You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
//        Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//        Return the maximum amount of water a container can store.
//
//        Notice that you may not slant the container.
//
//
//
//        Example 1:
//
//
//        Input: height = [1,8,6,2,5,4,8,3,7]
//        Output: 49
//        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//        Example 2:
//
//        Input: height = [1,1]
//        Output: 1
//
//
//        Constraints:
//
//        n == height.length
//        2 <= n <= 105
//        0 <= height[i] <= 104
class Solution {
    public int maxArea(int[] height) {
        int middle = findMiddle(height);
        int leftMax = indexOfLeftMax(height, middle);
        int rightMax = height.length - 1;
        int totalHeight = totalHeight(height[leftMax], height[rightMax]);
        int width = rightMax - leftMax;
        return width * totalHeight;
    }

    private int indexOfLeftMax(int[] height, int middle) {
        int index = 0;
        for (int i = 0; i < middle; i++) {
            if (height[i] > height[index]) {
                index = i;
            }
        }

        return index;
    }

    private int totalHeight(int leftMax, int rightMax) {
        return leftMax > rightMax ? rightMax : leftMax;
    }

    private int findMiddle(int[] height) {
        return height.length % 2 == 0 ? height.length / 2 : (height.length + 1) / 2;
    }
}