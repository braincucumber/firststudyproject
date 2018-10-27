package Lesson_8;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {
    @Test
    void Addition() {
        Calculator calculator = new Calculator(2, 3);
        double result = calculator.addition();
        assertEquals(5, result);
        calculator = new Calculator(-10, 4);
        result = calculator.addition();
        assertEquals(-6, result);
        calculator = new Calculator(-10, 0);
        result = calculator.addition();
        assertEquals(-10, result);
    }

    @Test
    void Substraction() {
        Calculator calculator = new Calculator(10, 9);
        double result = calculator.substraction();
        assertEquals(1, result);
        calculator = new Calculator(-100, 55);
        result = calculator.substraction();
        assertEquals(-155, result);
        calculator = new Calculator(100, 0);
        result = calculator.substraction();
        assertEquals(100, result);
    }

    @Test
    void Division() {
        Calculator calculator = new Calculator(10, 5);
        double result = calculator.division();
        assertEquals(2, result);
        calculator = new Calculator(-100, 50);
        result = calculator.division();
        assertEquals(-2, result);
    }

    @Test
    void Multiplication() {
        Calculator calculator = new Calculator(10, 5);
        double result = calculator.multiplication();
        assertEquals(50, result);
        calculator = new Calculator(-100, 55);
        result = calculator.multiplication();
        assertEquals(-5500, result);
        calculator = new Calculator(100, 0);
        result = calculator.multiplication();
        assertEquals(0, result);
    }

    @Test
    public void DivisionByZero() {
        try {
            Calculator calculator = new Calculator(0, 0);
            double result = calculator.division();
            fail("Expected an exception");
        } catch (ArithmeticException exception) {
            assertEquals("Arithmetic exception", exception.getMessage());
        }
    }
}
