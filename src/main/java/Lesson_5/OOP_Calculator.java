package Lesson_5;

import java.util.Scanner;

class Calculator {
    double firstNumber;
    double secondNumber;

    Calculator(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

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
        return firstNumber / secondNumber;
    }
}

public class OOP_Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number: ");
        double firstNumber = scanner.nextDouble();
        System.out.println("Enter operation: (+,-,*,/)");
        String operation = scanner.next();
        System.out.println("Enter second number: ");
        double secondNumber = scanner.nextDouble();
        double result = 0;
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
                    System.out.println("Incorrect entry");
            }
        }
        System.out.println("Answer is: " + result);
    }
}
