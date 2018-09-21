import java.util.Scanner;

/**
 * This is a simple calculator
 * @author Evgeniy Busygin
 */

public class CalculatorV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of task: (1 - calculator, 2 - string array)");
        int programSelect = scanner.nextInt();
        if (programSelect == 1) {
            System.out.println("Enter first number:");
            //Пользователь вводит первое число
            double firstNumber = scanner.nextDouble();
            System.out.println("Enter second number:");
            //Пользователь вводит второе число
            double secondNumber = scanner.nextDouble();
            System.out.println("Choose operation: 1(+) 2(-) 3(/) 4(*)");
            //Пользователь выбирает операцию
            byte sign = scanner.nextByte();
            switch (sign) {
                case 1:
                    System.out.printf("Answer is: %.4f", firstNumber + secondNumber);
                    break;
                case 2:
                    System.out.printf("Answer is: %.4f", firstNumber - secondNumber);
                    break;
                case 3:
                    System.out.printf("Answer is: %.4f", firstNumber / secondNumber);
                    break;
                case 4:
                    System.out.printf("Answer is: %.4f", firstNumber * secondNumber);
                    break;
                default:
                    System.out.println("Incorrect entry");
                    break;
            }
        }
        else if (programSelect == 2) {
            System.out.println("Enter number of array elements: ");
            int arrayLength = scanner.nextInt();
            String[] wordArray;
            wordArray = new String[arrayLength];
            for (int wordCount = 0; wordCount < wordArray.length; wordCount++) {
                System.out.println("Enter word №" + (wordCount + 1));
                String wordEntry = scanner.next();
                wordArray[wordCount] = wordEntry;
            }
            String longestWord = "";
            for (int wordCountLength = 0; wordCountLength < wordArray.length; wordCountLength++) {
                if (wordArray[wordCountLength].length() > longestWord.length()) {
                    longestWord = wordArray[wordCountLength];
                }
            }
            System.out.println("Longest word in array is: " + longestWord);
        }
        else {
            System.out.println("Incorrect entry");
        }
        scanner.close();
    }
}
