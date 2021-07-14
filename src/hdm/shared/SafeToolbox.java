package hdm.shared;

import java.util.Scanner;

public class SafeToolbox {

 public static void main(String[] args) {

	 int i = readIntSafe("Ganzzahl eingeben: ");
	 System.out.println("Ihre Eingabe war: " + i);
 }
 
 public static int readIntSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		int i = 0;
		try {
			return Integer.parseInt(string);
		}
	
		catch (NumberFormatException e) {
		System.out.printf("'%s' ist keine Ganzzahl. Bitte Ganzzahl eingeben.%n", string);
		i = readIntSafe(prompt);
	}
	return i;
 }
 public static byte readByteSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		byte b = 0;
		 try {
			 return Byte.parseByte(string);
		} catch (NumberFormatException e) {
			System.out.printf("'%s' ist kein Byte. Bitte Byte eingeben.%n", string);
			 b = readByteSafe(prompt);
		}
		return b;
	 }
 public static short readShortSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();	
		short s = 0;;
		try {
			 return Short.parseShort(string);
		} catch (NumberFormatException e) {
			System.out.printf("'%s' ist kein Short. Bitte Short eingeben.%n", string);
			 s = readShortSafe(prompt);
		}
		return s;
	 }
 public static long readLongSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		long l = 0;
	 try {
			return Long.parseLong(string);
		} catch (NumberFormatException e) {
			System.out.printf("'%s' ist kein Long. Bitte Long eingeben.%n", string);
			 l = readLongSafe(prompt);
		}
		return l;
	 }
 public static float readFloatSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		float f = 0;
		 try {
			return Float.parseFloat(string);
		} catch (NumberFormatException e) {
			System.out.printf("'%s' ist kein Float. Bitte Float eingeben.%n", string);
			 f = readFloatSafe(prompt);
		}
		return f;
	 }
 public static double readDoubleSafe(String prompt) {
	 System.out.print(prompt);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		double d = 0;
		 try {
			return Double.parseDouble(string);
		} catch (NumberFormatException e) {
			System.out.printf("'%s' ist kein Double. Bitte Double eingeben.%n", string);
			 d = readDoubleSafe(prompt);
		}
		return d;
	 }
}