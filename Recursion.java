import java.util.InputMismatchException;
import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the end value from the user
        int endVal = 0;
        boolean validEndVal = false;

        // Exception handling to make sure input is integer
        while (!validEndVal) {
            System.out.print("Enter the end value: ");
            try {
                endVal = scanner.nextInt();
                if (endVal >= 0) {
                    validEndVal = true;
                } else {
                    System.out.println("End value must be greater than or equal to 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }

        // Get the increment value from the user
        int incrementVal = 0;
        boolean validIncrementVal = false;

        // Exception handling to make sure input is integer

        while (!validIncrementVal) {
            System.out.print("Enter the increment value: ");
            try {
                incrementVal = scanner.nextInt();
                if (incrementVal > 0) {
                    validIncrementVal = true;
                } else {
                    System.out.println("Increment value must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // clear the input buffer
            }
        }

        // Calculate the sum of the sequence
        int sum = sumOfSequence(endVal, incrementVal);

        // Print the result
        System.out.println("Sum of the sequence: " + sum);
    }


    // Recursive function to calculate the sum of the sequence
    public static int sumOfSequence(int endVal, int incrementVal) {
        if (endVal <= 0) {
            return 0;
        } else {
            return endVal + sumOfSequence(endVal - incrementVal, incrementVal);
        }
    }
    
}