package dev.gokova.calculator;

public abstract class Calculator {

	public static Double calculate(Double operand1, Double operand2,
			Operator operator) {
		Double result;
		switch (operator) {
		case ADD:
			result = Add(operand1, operand2);
			break;
		case SUBTRACT:
			result = Subtract(operand1, operand2);
			break;
		case MULTIPLY:
			result = Multiply(operand1, operand2);
			break;
		case DIVIDE:
			result = Divide(operand1, operand2);
			break;
		case POWER:
			result = Power(operand1, operand2);
			break;
		default:
			result = (double) 0;
			break;
		}
		return result;
	}

	public static Double calculate(Double operand, Operator operator) {
		Double result;
		switch (operator) {
		case FACTORIAL:
			result = Factorial(operand);
			break;
		case SQUAREROOT:
			result = SquareRoot(operand);
			break;
		case SQUARE:
			result = Square(operand);
			break;
		default:
			result = (double) 0;
			break;
		}
		return result;
	}

	private static Double Add(Double operand1, Double operand2) {
		return operand1 + operand2;
	}

	private static Double Subtract(Double operand1, Double operand2) {
		return operand1 - operand2;
	}

	private static Double Multiply(Double operand1, Double operand2) {
		return operand1 * operand2;
	}

	private static Double Divide(Double operand1, Double operand2) {
		return operand1 / operand2;
	}

	private static Double Factorial(Double operand) {
		if (operand.equals((double) 0)) {
			return (double) 1;
		} else if (operand.equals((double) 2)) {
			return (double) 1;
		} else {
			Double result = (double) 1;
			for (int i = 2; i <= operand; i++) {
				result *= i;
			}
			return result;
		}
	}

	private static Double SquareRoot(Double operand) {
		return Math.sqrt(operand);
	}

	private static Double Square(Double operand) {
		return Math.pow(operand, 2);
	}

	private static Double Power(Double operand1, Double operand2) {
		return Math.pow(operand1, operand2);
	}
}
