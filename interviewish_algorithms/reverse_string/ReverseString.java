public class ReverseString {


    public static void main(String[] args) {
        String toReverse = "This is a reversable string";
        System.out.println(reverseStringWithAPI(toReverse));
        System.out.println(reverseStringManually(toReverse));
    }

    public static String reverseStringWithAPI(String toReverse) {
        return new StringBuilder(toReverse)
                .reverse()
                .toString();
    }

    public static String reverseStringManually(String toReverse) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int iteration = toReverse.length() - 1; iteration >= 0; iteration--) {
            stringBuilder.append(toReverse.charAt(iteration));
        }
        return stringBuilder.toString();
    }
}
