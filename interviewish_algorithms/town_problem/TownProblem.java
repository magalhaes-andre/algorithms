/*
Context
The following problem will help us measure your ability on solving AD HOC problems.

Problem description
It's raining in our town for some days. The government has determined a way to inform the population about the problem, R means that that day it was rainy, S means that it was sunny:
SRRS means that
S => the first day was sunny
R => the second day was rainy
R => the third day was rainy
S => the fourth day was sunny.
You are interested in knowing what was the longest rainy season, for the current example, it is 2.

Task & Objectives
Complete the function:

  public static int solve(String input) {
      return 0;
  }
You are encouraged to add more unit tests and modify existing ones.

Input and Output
A String will be given to you. The maximun size of the String is 100.
Return a number >= 0 and <= 100
 */
class TownProblem {

    public static void main(String[] args) {
        String rainRegistry = "RRSSSRRRRRRRRSSSSRSSSRSSSRSSSSRRRRR";
        System.out.println(solve(rainRegistry));
    }

    public static int solve(String input) {
        char[] characters = input.toCharArray();
        int currentCounter = 0;
        int maxCounter = 0;
        for(int iteration = 0; iteration < characters.length; iteration++){
            if (String.valueOf(characters[iteration]).equalsIgnoreCase("R")) {
                currentCounter++;
            } else {
                currentCounter = 0;
            }

            if(currentCounter > maxCounter) {
                maxCounter = currentCounter;
            }
        }
        return maxCounter;
    }
}