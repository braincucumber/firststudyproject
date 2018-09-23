import java.util.Scanner;

/**
 * This program contains calculator and finder of longest word in an array.
 * @author Evgeniy Busygin
 */

public class CalculatorV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of task: (1 - calculator, 2 - string array)");
        //Пользователь выбирает какую из программ запустить
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
                    System.out.printf("Answer is: %.4f", firstNumber + secondNumber); // Сложение
                    break;
                case 2:
                    System.out.printf("Answer is: %.4f", firstNumber - secondNumber); // Вычитание
                    break;
                case 3:
                    System.out.printf("Answer is: %.4f", firstNumber / secondNumber); // Деление
                    break;
                case 4:
                    System.out.printf("Answer is: %.4f", firstNumber * secondNumber); // Умножение
                    break;
                default:
                    System.out.println("Incorrect entry"); // Введена неверная команда
                    break;
            }
        }
        else if (programSelect == 2) {
            System.out.println("Enter number of array elements: ");
            // Пользователь вводит количество элементов в массиве
            int arrayLength = scanner.nextInt();
            String[] wordArray;
            wordArray = new String[arrayLength];
            for (int wordCount = 0; wordCount < wordArray.length; wordCount++) {
                System.out.println("Enter word №" + (wordCount + 1));
                // Пользователь вводит количество слов = длине массива
                String wordEntry = scanner.next();
                wordArray[wordCount] = wordEntry;
            }
            String longestWord = "";
            for (int wordCountLength = 0; wordCountLength < wordArray.length; wordCountLength++) {
                if (wordArray[wordCountLength].length() > longestWord.length()) {
                    // Производится поиск самого длинного слова
                    longestWord = wordArray[wordCountLength];
                }
            }
            System.out.println("Longest word in array is: " + longestWord);
        }
        else {
            System.out.println("Incorrect entry"); // Введена некорректная команда
        }
        scanner.close();
    }
}
