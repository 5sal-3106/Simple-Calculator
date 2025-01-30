package simplejavacalculator;

import static java.lang.Double.NaN;
import static java.lang.Math.*;

import java.util.Scanner;

public class Calculator {

    public enum BiOperatorModes {
        normal, add, minus, multiply, divide, xpowerofy
    }

    public enum MonoOperatorModes {
        square, squareRoot, oneDividedBy, cos, sin, tan, log, rate, abs
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.normal;

    private Double calculateBiImpl() {
        switch (mode) {
            case normal:
                return num2;
            case add:
                return num1 + num2;
            case minus:
                return num1 - num2;
            case multiply:
                return num1 * num2;
            case divide:
                return (num2 == 0) ? NaN : num1 / num2; // Prevent division by zero
            case xpowerofy:
                return pow(num1, num2);
            default:
                throw new Error("Unknown operation");
        }
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;
        return NaN;
    }

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        switch (newMode) {
            case square:
                return num * num;
            case squareRoot:
                return (num < 0) ? NaN : sqrt(num); // Prevent square root of negative numbers
            case oneDividedBy:
                return (num == 0) ? NaN : 1 / num; // Prevent division by zero
            case cos:
                return cos(toRadians(num));
            case sin:
                return sin(toRadians(num));
            case tan:
                if (num % 180 == 0) return 0.0;
                if (num % 90 == 0 && num % 180 != 0) return NaN; // Undefined for 90°, 270°, etc.
                return tan(toRadians(num));
            case log:
                return (num <= 0) ? NaN : log10(num); // Logarithm of non-positive numbers is undefined
            case rate:
                return num / 100;
            case abs:
                return abs(num); // Added missing absolute value case
            default:
                throw new Error("Unknown operation");
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Java Calculator");
        System.out.println("Choose operation: +, -, *, /, ^ (power), sqrt, square, 1/x, sin, cos, tan, log, %, abs");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter operation: ");
        String operation = scanner.next();

        double result;

        if (operation.equals("+")) {
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            result = calculator.calculateBi(BiOperatorModes.add, num1);
            result = calculator.calculateEqual(num2);
        } else if (operation.equals("-")) {
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            result = calculator.calculateBi(BiOperatorModes.minus, num1);
            result = calculator.calculateEqual(num2);
        } else if (operation.equals("*")) {
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            result = calculator.calculateBi(BiOperatorModes.multiply, num1);
            result = calculator.calculateEqual(num2);
        } else if (operation.equals("/")) {
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            result = calculator.calculateBi(BiOperatorModes.divide, num1);
            result = calculator.calculateEqual(num2);
        } else if (operation.equals("^")) {
            System.out.print("Enter exponent: ");
            double num2 = scanner.nextDouble();
            result = calculator.calculateBi(BiOperatorModes.xpowerofy, num1);
            result = calculator.calculateEqual(num2);
        } else if (operation.equals("sqrt")) {
            result = calculator.calculateMono(MonoOperatorModes.squareRoot, num1);
        } else if (operation.equals("square")) {
            result = calculator.calculateMono(MonoOperatorModes.square, num1);
        } else if (operation.equals("1/x")) {
            result = calculator.calculateMono(MonoOperatorModes.oneDividedBy, num1);
        } else if (operation.equals("sin")) {
            result = calculator.calculateMono(MonoOperatorModes.sin, num1);
        } else if (operation.equals("cos")) {
            result = calculator.calculateMono(MonoOperatorModes.cos, num1);
        } else if (operation.equals("tan")) {
            result = calculator.calculateMono(MonoOperatorModes.tan, num1);
        } else if (operation.equals("log")) {
            result = calculator.calculateMono(MonoOperatorModes.log, num1);
        } else if (operation.equals("%")) {
            result = calculator.calculateMono(MonoOperatorModes.rate, num1);
        } else if (operation.equals("abs")) {
            result = calculator.calculateMono(MonoOperatorModes.abs, num1);
        } else {
            System.out.println("Invalid operation!");
            scanner.close();
            return;
        }

        System.out.println("Result: " + result);
        scanner.close();
    }
}
