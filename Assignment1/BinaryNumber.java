package Assignments;

/***
 * * @author : Sangram Thorat (CWID : 70007345) * This program will accept
 * Binary number input. * Perform basic operations like shift to Right binary
 * number, convert binary number to decimal and addition of two Binary numbers.
 */

// Creation of Class Binary Number
public class BinaryNumber {
	// Declaration of private integer data array
	private int data[];
	private boolean overflow = false;

	// Constructor of integer length for binary number of 0
	public BinaryNumber(int length) {

		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = 0;
		}
		for (int i = 0; i < length; i++) {
			System.out.print(data[i]);

		}
		System.out.println();
	}

	// Constructor of String str value for input binary number
	public BinaryNumber(String str) {

		data = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			data[i] = Character.getNumericValue(str.charAt(i));

		}
		for (int i = 0; i < str.length(); i++) {
			System.out.print(data[i]);
		}

		System.out.println();

	}

	// Get length method to calculate length of Constructor
	public int getLength() {
		return data.length;

	}

	// getDigit int method for return of digit at particular index
	public int getDigit(int index) {

		if (index > data.length) {
			return 0;
		} else {
			return data[index];
		}
	}

	// Shift binary number by given amount
	public void shiftR(int amount) {

		int arr[] = new int[data.length + amount];
		for (int i = 0; i < data.length; i++) {
			arr[i + amount] = data[i];
		}

		for (int i = 0; i < amount; i++) {
			arr[i] = 0;
		}

		data = arr;

	}

	// addition of binary number
	void add(BinaryNumber aBinaryNumber) {
		int C = 0;
		int sum_of_Bin = 0;
		int answer[] = new int[data.length];

		int Bin1 = aBinaryNumber.getLength();

		int Bin2 = data.length;

		if (Bin1 == Bin2) {
			for (int i = 0; i < data.length; i++) {
				sum_of_Bin = (aBinaryNumber.getDigit(i) + data[i] + C) % 2;
				C = (aBinaryNumber.getDigit(i) + data[i] + C) / 2;
				answer[i] = sum_of_Bin;
			}

			if (C == 0) {
				for (int i = 0; i < data.length; i++) {

					data[i] = answer[i];
				}
				System.out.println("Addition of the two numbers is: ");
				for (int i = 0; i < data.length; i++) {
					System.out.print(data[i]);
				}
			} else {
				overflow = true;
				System.out.println("Overflow");
			}

		} else {
			System.out.println("The lengths are not equivalents");
		}

	}

	// toString method to convert integer binary number to string
	public String toString() {
		String str = "";

		for (int i = 0; i < getLength(); i++) {

			str += data[i];
		}
		if (overflow) {
			return "Overflow";
		} else {
			return str;
		}

	}

	// toDecemle method to convert binary number to decimle number
	public int toDecimal() {

		int a = 0;
		int Dec = 0;
		for (int i = 0; i < data.length; i++) {

			Dec += (int) (data[i] * Math.pow(2, a));
			a++;
		}

		return Dec;
	}

	// Clear overflow method
	public void clearOverflow() {
		overflow = false;
	}

	//main method
	public static void main(String[] args) {

		BinaryNumber b1 = new BinaryNumber(4);
		BinaryNumber b2 = new BinaryNumber("11100");

//		System.out.println("Length of Binary number: " + b1.getLength());
//		System.out.println("Get given Digit of Binary number: " + b1.getDigit(3));
//		System.out.println("Decimal number of Binary number: " + b1.toDecimal());
//		System.out.println("Binary number after ShiftR: ");
//		b2.shiftR(3);
//		System.out.println(b1.toString());
		b2.add(b2);
//		System.out.println("\ntoString representation of Binary Number: " + b1.toString());

	}// end of main

}// end of class
