package yodleintern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {
	private static BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

	private static BufferedReader fio = null;
	private static BufferedWriter fil = null;

	public static boolean openFile(final String filename) {

		try {
			fio = new BufferedReader(new FileReader(filename));
			return true;
		} catch (final IOException e) {
			return false;
		}
	}

	public static boolean newFile(final String filename) {
		try {
			fil = new BufferedWriter(new FileWriter(filename));
			return true;
		} catch (final IOException e) {
			return false;
		}

	}

	public static void writeLine(final String write) {
		if (fil == null)
			return;

		try {
			fil.write(write);
			fil.flush();
		} catch (final IOException e) {
			return;
		}

	}

	public static String readLine() {
		if (fio == null)
			return null;

		try {
			return fio.readLine();
		} catch (final IOException e) {
			return null;
		}
	}

	public static String readString() {
		while (true) {
			try {
				return kb.readLine();
			} catch (final IOException e) {
				// should never happen
			}
		}
	}

	public static int readInt() {
		while (true) {
			try {
				final String s = kb.readLine();
				return Integer.parseInt(s);
			} catch (final NumberFormatException e) {
				System.out.print("That is not an integer.  Enter again: ");
			} catch (final IOException e) {
				// should never happen
			}
		}
	}

	public static double readDouble() {
		while (true) {
			try {
				final String s = kb.readLine();
				return Double.parseDouble(s);
			} catch (final NumberFormatException e) {
				System.out.print("That is not a number.  Enter again: ");
			} catch (final IOException e) {
				// should never happen
			}
		}
	}

	public static char readChar() {
		String s = null;

		try {
			s = kb.readLine();
		} catch (final IOException e) {
			// should never happen
		}

		while (s.length() != 1) {
			System.out.print("That is not a single character.  Enter again: ");
			try {
				s = kb.readLine();
			} catch (final IOException e) {
				// should never happen
			}
		}

		return s.charAt(0);
	}

	public static boolean readBoolean() {
		String s = null;

		while (true) {
			try {
				s = kb.readLine();
			} catch (final IOException e) {
				// should never happen
			}

			if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y") || s.equalsIgnoreCase("true")
					|| s.equalsIgnoreCase("t")) {
				return true;
			} else if (s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n") || s.equalsIgnoreCase("false")
					|| s.equalsIgnoreCase("f")) {
				return false;
			} else {
				System.out.print("Enter \"yes\" or \"no\": ");
			}
		}
	}

	public static void outputStringAnswer(final String s) {
		if (s != null) {
			System.out.println("RESULT: \"" + s + "\"");
		} else {
			System.out.println("RESULT: null");
		}
	}

	public static void outputIntAnswer(final int i) {
		System.out.println("RESULT: " + i);
	}

	public static void outputDoubleAnswer(final double d) {
		System.out.println("RESULT: " + d);
	}

	public static void outputCharAnswer(final char c) {
		System.out.println("RESULT: '" + c + "'");
	}

	public static void outputBooleanAnswer(final boolean b) {
		System.out.println("RESULT: " + b);
	}

	public static void reportBadInput() {
		System.out.println("User entered bad input.");
	}
}

