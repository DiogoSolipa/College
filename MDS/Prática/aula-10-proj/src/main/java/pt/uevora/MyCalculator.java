package pt.uevora;

import java.util.Scanner;

public class MyCalculator {

    public Double execute(String expression)
    {
        String[] split = expression.split("\\+");

        if(expression.contains("+"))
        {
            return sum(split[0], split[1]);
        }

        throw new IllegalArgumentException("Invalid expression!");
    }

    private Double sum(String arg1, String arg2) {
        return new Double(arg1) + new Double(arg2);
    }

    public static void main(String[] args) {

        System.out.println("Calculator");
        System.out.println("Enter your expression:");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        MyCalculator myCalculator = new MyCalculator();
        Object result = myCalculator.execute(expression);

        System.out.println("Result :  " + result);
    }

}
