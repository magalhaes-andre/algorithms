/*
FizzBuzz is a common type of interview challenge, quite a simple one actually.

The program that implements it should be able to:
 1. Print numbers from 0 to n
 2. Print Fizz for multiples of x
 3. Print Buzz for multiples of y
 4. Print Fizz Buzz for multiples of x and y simultaneously
 5. Print the number itself if none applies

 */
public class FizzBuzz {

    private static final String fizz = "Fizz";
    private static final String buzz = "Buzz";
    private static final String fizzBuzz = "Fizz Buzz";
    public static void main(String[] args) {
        try {
            int n = Integer.valueOf(args[0]);
            int x = Integer.valueOf(args[1]);
            int y = Integer.valueOf(args[2]);
            System.out.println("Value of n is: " + n);
            System.out.println("Value of x is: " + x);
            System.out.println("Value of y is: " + y);
            fizzBuzz(n, x, y);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("There were missing arguments when running, you need to provide a value for n, x and y.");
        }

    }

    private static void fizzBuzz(int n, int x, int y) {

        for (int iteration = 0; iteration <= n; iteration ++) {
            if (checkMultiple(iteration, x) && checkMultiple(iteration, y)){
                System.out.println(fizzBuzz);
            } else if (checkMultiple(iteration, x)) {
                System.out.println(fizz);
            } else if (checkMultiple(iteration, y)) {
                System.out.println(buzz);
            } else {
                System.out.println(iteration);
            }
        }
    }

    private static boolean checkMultiple(int numberToCheck, int multiplier) {
        return numberToCheck % multiplier == 0;
    }
}
