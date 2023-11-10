public class LongestPalindromicSubstring {
    //    Given a string, find the longest substring which is a palindrome.
//    Input: Given string :"forgeeksskeegfor",
//    Output: "geeksskeeg".
//    Input: Given string :"Geeks",
//    Output: "ee".
    public static void main(String[] args) {
        String input = "forgeeksskeegfor";
        System.out.println(bruteForceApproach(input));
        System.out.println(longestPalindrome(input));
    }

//    Create a 2-D array to fill the array with appropriate steps.
//    Fill the matrix using the gap method where we fill the matrix in a diagonal way .
//    At every step ,we check if the substring generated has meet the condition of palindrome or not.
//    At every step, we keep a counter variable to store the max length of the palindrome string achieved so far.
//    Atlast return the ans.
//    This one is also called "Tabulation Method"
//    Time Complexity:  O(N^2), where N is the length of string
//    Space Complexity: O(N^2) since have created a 2-D array.
    public static String bruteForceApproach(String input) {

        int counter = 0;
        int inputLength = input.length();
        String palindrome = "";
        boolean[][] palindromeTracker = new boolean[inputLength][inputLength];

        for (int iteration = 0; iteration < inputLength; iteration++) {
            for (int firstNestedIteration = 0, secondNestedIteration = iteration; secondNestedIteration < inputLength; firstNestedIteration++, secondNestedIteration++) {
//                Check if substring is of length 1
                if (iteration == 0) {
                    palindromeTracker[firstNestedIteration][secondNestedIteration] = true;
                } else if ( iteration == 1) {
//                    Check if the substring is of length 2
                    palindromeTracker[firstNestedIteration][secondNestedIteration] = (input.charAt(firstNestedIteration) == input.charAt(secondNestedIteration));
                } else {
//                    Check if the current substring is a palindrome
//                    based on its ends
                    palindromeTracker[firstNestedIteration][secondNestedIteration] = (input.charAt(firstNestedIteration) == input.charAt(secondNestedIteration) && palindromeTracker[firstNestedIteration + 1][secondNestedIteration - 1]);
                }

                if (palindromeTracker[firstNestedIteration][secondNestedIteration] && counter < input.substring(firstNestedIteration, secondNestedIteration + 1).length()) {
                    palindrome = input.substring(firstNestedIteration, secondNestedIteration + 1);
                    counter = palindrome.length();
                }
            }
        }
        return palindrome;
    }

//    Maintain a variable maxLength = 1 (for storing LPS length) and ‘ start =0 ‘ (for storing starting index of LPS ).
//    The idea is very simple, we will traverse through the entire string with i=0 to i<(length of string).
//      while traversing, initialize ‘low‘ and ‘high‘ pointer such that low= i-1 and high= i+1.
//      keep incrementing ‘high’ until str[high]==str[i] .
//      similarly keep decrementing ‘low’ until str[low]==str[i].
//      finally we will keep incrementing ‘high’ and decrementing ‘low’ until str[low]==str[high].
//      calculate length=high-low-1, if length > maxLength then maxLength = length and start = low+1 .
//    Print the LPS and return maxLength.
    public static String longestPalindrome(String input) {
        if (input == null || input.length() < 1) return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < input.length(); i++) {
            int evenLength = expandFromMiddle(input, i, i);
            int oddLength = expandFromMiddle(input, i, i+1);
            int length = Math.max(evenLength, oddLength);
            if (length > end - start) {
                start = i - ((length - 1) / 2);
                end = i + (length / 2);
            }
        }
        return input.substring(start, end + 1);
    }

    private static int expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right) {
            return 0;
        }

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
