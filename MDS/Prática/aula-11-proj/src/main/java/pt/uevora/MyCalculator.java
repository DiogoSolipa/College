package pt.uevora;

import java.util.Scanner;

public class MyCalculator {

    public Double execute(String expression){
        String[] split = expression.split("[\\+\\-\\*\\/\\^\\%]");

        if(expression.contains("+"))
        {
            return sum(split[0], split[1]);
        }
        else if(expression.contains("-"))
        {
            return sub(split[0], split[1]);
        }
        else if(expression.contains(("*")))
        {
            return mult(split[0], split[1]);
        }
        else if(expression.contains(("/")))
        {
            return div(split[0], split[1]);
        }
        else if(expression.contains(("%")))
        {
            return remain(split[0], split[1]);
        }

        throw new IllegalArgumentException("Invalid expression!");
    }

    private Double sum(String arg1, String arg2) {
        return Double.valueOf(arg1) + Double.valueOf(arg2);
    }
    private Double sub(String arg1, String arg2) {
        return Double.valueOf(arg1) - Double.valueOf(arg2);
    }
    private Double mult(String arg1, String arg2) {
        return Double.valueOf(arg1) * Double.valueOf(arg2);
    }
    private Double div(String arg1, String arg2) {
        return Double.valueOf(arg1) / Double.valueOf(arg2);
    }
    private Double remain(String arg1, String arg2) {
        return Double.valueOf(arg1) % Double.valueOf(arg2);
    }

    public static void main(String[] args) {
        System.out.println("Calculator");
        System.out.println("Enter your expression:");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        MyCalculator myCalculator = new MyCalculator();
        Object result = myCalculator.execute(expression);

        System.out.println("Result :  " + result);
        scanner.close();
        
    }

}
