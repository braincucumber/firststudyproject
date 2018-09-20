import java.util.Scanner;

/**
 * This is a simple calculator
 *
 * @author Evgeniy Busygin
 * @return sum of two numbers
 */
public class Calculator {
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
