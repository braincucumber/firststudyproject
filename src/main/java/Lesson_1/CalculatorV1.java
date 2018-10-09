package Lesson_1;

import java.util.Scanner;

/**
 * This is a simple sum calculator
 *
 * @author Evgeniy Busygin
 * @return sum of two numbers
 */
public class CalculatorV1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите первое число:");
        //Пользователь вводит первое число
        double firstNumber = scanner.nextDouble();
        System.out.println("Введите второе число:");
        //Пользователь вводит второе число
        double secondNumber = scanner.nextDouble();
        double sum = firstNumber + secondNumber;
        //Выводится сумма двух чисел
        System.out.printf("Сумма чисел равна: %.4f", sum);
        scanner.close();
    }
}
