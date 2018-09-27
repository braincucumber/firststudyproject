import java.util.Scanner;

/**
 * This program finds smallest positive and biggest negative number in an array and swaps them.
 *
 * @author Evgeniy Busygin
 */

public class ArrayLesson4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrayOfNumbers;
        arrayOfNumbers = new int[4];
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            System.out.println("Enter element №" + (i + 1));
            // Пользователь вводит количество элементов = длине массива
            int numberEntry = scanner.nextInt();
            arrayOfNumbers[i] = numberEntry;
        }
        int maxNegElement = -11; //Переменная, отображающая самое большое отрицательное число в массиве (Значение по умолчанию = -11, т.к. все значения в массиве от 10 до -10)
        int maxNegElementPosition = 0; //Позиция этой переменной в массиве
        int minPosElement = 11; //Переменная, отображающая самое маленько положительное число в массиве (Значение по умолчанию = 11, т.к. все значения в массиве от 10 до -10)
        int minPosElementPosition = 0; // Позиция этой переменной в массиве
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            if (arrayOfNumbers[i] < 0 && arrayOfNumbers[i] > maxNegElement) {
                maxNegElement = arrayOfNumbers[i];
                maxNegElementPosition = i;
            }
            if (arrayOfNumbers[i] > 0 && arrayOfNumbers[i] < minPosElement) {
                minPosElement = arrayOfNumbers[i];
                minPosElementPosition = i;
            }
        }
        arrayOfNumbers[maxNegElementPosition] = minPosElement;
        arrayOfNumbers[minPosElementPosition] = maxNegElement;
        // Если переменные не получили новых значений, то операцию невозможно произвести
        if (maxNegElement != -11 && minPosElement != 11) {
            System.out.println("Maximal negative element in array is: " + maxNegElement + " on position № " + maxNegElementPosition);
            System.out.println("Minimal positive elements in array is: " + minPosElement + " on position № " + minPosElementPosition);
            System.out.println("Array with switched elements :");
            for (int i : arrayOfNumbers
            ) {
                System.out.print(i + " ");
            }
        } else if (maxNegElement == -11) { //Если значение переменной осталось -11, значит в массиве отсутствуют отрицательные числа
            System.out.println("There is no negative elements in array");
        } else if (minPosElement == 11) { // Если значение переменной осталось 11, значит в массиве отсутствуют положительные числа
            System.out.println("There is no positive elements in array");
        }
    }
}
