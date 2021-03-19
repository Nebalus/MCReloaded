package io.pixelstudios.libary;

public class MathLibary {

	public static double roundUp(double number) {
		number = number * 10;
		number = Math.round(number);
		number = number / 10;
		return number;
	}
	
	public static double getPercent(double number, double maxNumber) {
		return number*100/maxNumber;
	}
}
