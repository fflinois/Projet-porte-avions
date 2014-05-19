package com.porte_avions.prog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() {
        String tmp = "";

        try {
            tmp = in.readLine();
        } catch (final IOException e) {
            exceptionHandler(e);
        }
        return tmp;
    }

    public static int readInt() {
        int x = 0;

        try {
            x = Integer.parseInt(readString());
        } catch (final NumberFormatException e) {
            exceptionHandler(e);
        }

        return x;
    }

    public static boolean readBoolean() {
        boolean b = true;

        try {
            b = Boolean.valueOf(readString()).booleanValue();
        } catch (final NumberFormatException e) {
            exceptionHandler(e);
        }

        return b;
    }

    public static double readDouble() {
        double x = 0.0;

        try {
            x = Double.valueOf(readString()).doubleValue();
        } catch (final NumberFormatException e) {
            exceptionHandler(e);
        }

        return x;
    }

    public static char readChar() {
        final String tmp = readString();

        if (tmp.length() == 0)
            return '\n';
        else {
            return tmp.charAt(0);
        }
    }

    public static void writeString(final String s) {
        try {
            System.out.print(s);
        } catch (final Exception ex) {
            exceptionHandler(ex);
        }
    }

    public static void writeStringln(final String s) {
        writeString(s);
        lineBreak();
    }

    public static void writeInt(final int i) {
        writeString("" + i);
    }

    public static void writeIntln(final int i) {
        writeString("" + i);
        lineBreak();
    }

    public static void writeBoolean(final boolean b) {
        writeString("" + b);
    }

    public static void writeBooleanln(final boolean b) {
        writeString("" + b);
        lineBreak();
    }

    public static void writeDouble(final double d) {
        writeString("" + d);
    }

    public static void writeDoubleln(final double d) {
        writeDouble(d);
        lineBreak();
    }

    public static void writeChar(final char c) {
        writeString("" + c);
    }

    public static void writeCharln(final char c) {
        writeChar(c);
        lineBreak();
    }

    public static void lineBreak() {
        try {
            System.out.println();
        } catch (final Exception ex) {
            exceptionHandler(ex);
        }
    }

    protected static void exceptionHandler(final Exception ex) {
        final TerminalException err = new TerminalException(ex);
        throw err;
    }

    public static void writeException(final Throwable ex) {
        writeString(ex.toString());
        ex.printStackTrace(System.err);
    }
}

class TerminalException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Exception ex;

    TerminalException(final Exception e) {
        ex = e;
    }
}