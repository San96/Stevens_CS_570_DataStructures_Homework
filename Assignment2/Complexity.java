package Assignments;
/**
 * 
 */

/**
 * @author Sangram Thorat CWID : 20007345
 *
 */
public class Complexity {
	static int tempVar = -1;

	/**
	 * @param args
	 * @return
	 */

	// a method that has time complexity O(n2).

	public static void method1(int n) {
		int counter = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;

			}
		}

	}
	// a method that has time complexity O(n3).

	public static void method2(int n) {

		int counter = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}

			}
		}

	}
	// a method that has time complexity O(logn).

	public static void method3(int n) {

		int counter = 1;
		for (int i = 2; i < n; i = i * 2) {
			System.out.println("Operation " + counter);
			counter++;

		}
	}

	// a method that has time complexity O(nlogn).

	public static void method4(int n) {

		int counter = 1;
		for (int j = 0; j < n; j++) {
			for (int i = 2; i <= n; i *= 2) {
				System.out.println("Operation " + counter);
				counter++;

			}
		}

	}

	// a method that has time complexity O(loglogn).

	public static void method5(int n) {
		int counter = 1;
		for (int i = 2; i < n; i = i * i) {
			System.out.println("Operation " + counter);
			counter++;
		}

	}

	// a method that has time complexity O(2n).
	public static int method6(int n) {
		tempVar++;
		if (n == 1) {
			System.out.println("Operation" + " " + tempVar);
			tempVar++;
			System.out.println("Operation" + " " + tempVar);
			return tempVar;
		} else {
			method6(n - 1);
			method6(n - 1);
			return tempVar;
		}

	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Complexity a = new Complexity();
		a.method1(2);
		a.method2(2);
		a.method3(20);
		a.method4(16);
		a.method5(64);
		a.method6(2);
	}

}
