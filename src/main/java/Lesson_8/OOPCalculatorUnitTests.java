package Lesson_8;

import java.util.InputMismatchException;
import java.util.Scanner;

class Calculator {
    double firstNumber;
    double secondNumber;

    Calculator(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    // Описываем операции
    double addition() {
        return firstNumber + secondNumber;
    }

    double substraction() {
        return firstNumber - secondNumber;
    }

    double multiplication() {
        return firstNumber * secondNumber;
    }

    double division() {
        if (secondNumber == 0) {
            throw new ArithmeticException("Arithmetic exception");
        }
        return firstNumber / secondNumber;
    }
}

public class OOPCalculatorUnitTests {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number: ");
        double firstNumber = scanner.nextDouble(); // Вводим первое число
        System.out.println("Enter operation: (+,-,*,/)");
        String operation = scanner.next(); // Вводим знак операции
        System.out.println("Enter second number: ");
        double secondNumber = scanner.nextDouble(); // Вводим второе число
        double result = 0;
        try { // Вводим механизм исключительных ситуаций
            Calculator calculator = new Calculator(firstNumber, secondNumber);
            {
                switch (operation) {
                    case "+":
                        result = calculator.addition();
                        break;
                    case "-":
                        result = calculator.substraction();
                        break;
                    case "*":
                        result = calculator.multiplication();
                        break;
                    case "/":
                        result = calculator.division();
                        break;
                    default:
                        System.out.println("Incorrect operator entry");
                        break;
                }
            }

            System.out.println("Answer is: " + result);
        } catch (InputMismatchException exceptionTwo) { // Отлавливаем некорректный ввод данных
            System.out.println("Exception thrown: " + exceptionTwo + "\n Input is not a double");
        }
    }
}