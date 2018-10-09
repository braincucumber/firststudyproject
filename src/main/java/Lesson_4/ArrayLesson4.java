package Lesson_4;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This program finds smallest positive and biggest negative number in an array and swaps them.
 *
 * @author Evgeniy Busygin
 */

public class ArrayLesson4 {
    public static void main(String[] args) {
        int[] arrayOfNumbers = ThreadLocalRandom.current().ints(-10, 10).distinct().limit(20).toArray(); //Случайная генерация массива с уникальными числами
        for (int i : arrayOfNumbers) {
            System.out.print(i + " "); // Вывод каждого элемента массива в консоль
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
        // Меняем элементы местами
        arrayOfNumbers[maxNegElementPosition] = minPosElement;
        arrayOfNumbers[minPosElementPosition] = maxNegElement;
        System.out.println("\nMaximal negative element in array is: " + maxNegElement + " on position № " + maxNegElementPosition);
        System.out.println("Minimal positive elements in array is: " + minPosElement + " on position № " + minPosElementPosition);
        System.out.println("Array with switched elements :");
        for (int i : arrayOfNumbers
        ) {
            System.out.print(i + " ");
        }
    }
}
