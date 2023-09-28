import java.util.HashMap;
import java.util.Map;

/*
The TwoSum problem is still an easy but quite interesting one.
Also, it is quite a common one to appear on most of the quick coding challenge interviews.

A common definition of the problem would be similar to:

"Given an array of integers and an integer target, return the indices of two numbers that add up to the target."

Therefore, what is expected from a TwoSum program, is to:

1. Identify the numbers in the array
2. Identify what is the target value
3. Find in the array which indices will sum up to the target value
4. Return those indices at the end
 */
public class TwoSum {

    private static final int numbers[] = new int[]{2, 3, 7, 4, 8};
    private static final int  target = 7;
    public static void main(String[] args) {
        int[] result = dictionaryApproach(numbers, target);
        System.out.println(result[0] + " " + result[1]);
    }

    /*
    This one is the brute force approach simply because it takes the most time possible, going through each and every combination.
    The complexity of this solution would be O(n²) because ... TODO
     */
    private static int[] bruteForceApproach(int[] numbers, int target) {
        for (int iteration = 0; iteration < numbers.length; iteration++) {
            for (int innerIteration = iteration +1; innerIteration < numbers.length; innerIteration ++) {
                if (meetsTarget(numbers[iteration], numbers[innerIteration], target)) {
                    return new int[]{iteration, innerIteration};
                }
            }
        }
        return null;
    }

    private static int[] dictionaryApproach(int[] numbers, int target) {
        //Map<Integer, Integer> dictionary = fillDictionary(numbers);
        Map<Integer, Integer> dictionary = new HashMap<>();
        for (int iteration = 0; iteration < numbers.length; iteration++) {
            int differenceToTarget = target - numbers[iteration];
            if(dictionary.containsKey(differenceToTarget)) {
                return new int[]{iteration, dictionary.get(differenceToTarget)};
            }
            dictionary.put(numbers[iteration], iteration);
        }
        return null;
    }

    /*
    If dictionary is filled separetely, we loose the possibility of finding the pair without going through the entire array.
     */
    private static Map<Integer,Integer> fillDictionary(int[] numbers) {
        Map<Integer, Integer> dictionary = new HashMap<>();

        for (int iteration = 0; iteration < numbers.length; iteration++) {
            dictionary.put(numbers[iteration], iteration);
        }
        return dictionary;
    }

    private static boolean meetsTarget(int firstValue, int secondValue, int target) {
        return firstValue + secondValue == target;
    }
}
