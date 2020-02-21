package task1;

import java.util.Scanner;

public class Calculator {
	
	private static Scanner scan;

	public static void main(final String[] args) {
		
		scan = new Scanner(System.in);
		String message = "Supported format:\nval1 + val2\nval1 - val2\nval1 * val2\nval1 / val2\n";
		String operation = null;
		double valueOne = 0.0;
		double valueTwo = 0.0;

		System.out.println(message);

		try {
			String[] expression = scan.nextLine().split(" ");
			operation = expression[1];
			valueOne = Double.parseDouble(expression[0]);
			valueTwo = Double.parseDouble(expression[2]);
			double result = 0.0;
			
			switch (operation) {
				case "*":
					result = valueOne * valueTwo;
					System.out.println("= " + result);
					break;
				case "+":
					result = valueOne + valueTwo;
					System.out.println("= " + result);
					break;
				case "-":
					result = valueOne - valueTwo;
					System.out.println("= " + result);
					break;
				case "/":
					result = valueOne / valueTwo;
					System.out.println("= " + result);
					break;
				default:
					System.out.println("Unknown operation!\n" + message);
					break;
			}
		} catch (Exception e) {
			System.err.println("Invalid expression format.\n" + message);
		}
	}
}