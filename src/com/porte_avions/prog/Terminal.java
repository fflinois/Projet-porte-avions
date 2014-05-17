package com.porte_avions.prog;
import java.io.*;

public class Terminal {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() {
        String tmp = "";

        try {
            tmp = in.readLine();
        } catch (IOException e) {
            exceptionHandler(e);
        }
        return tmp;
    }

    public static int readInt() {
        int x = 0;

        try {
            x = Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }

        return x;
    }

    public static boolean readBoolean() {
        boolean b = true;

        try {
            b = Boolean.valueOf(readString()).booleanValue();
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }

        return b;
    }

    public static double readDouble() {
        double x = 0.0;

        try {
            x = Double.valueOf(readString()).doubleValue();
        } catch (NumberFormatException e) {
            exceptionHandler(e);
        }

        return x;
    }

    public static char readChar() {
        String tmp = readString();

        if (tmp.length() == 0)
            return '\n';
        else {
            return tmp.charAt(0);
        }
    }

    public static void writeString(String s) {
        try {
            System.out.print(s);
        } catch (Exception ex) {
            exceptionHandler(ex);
        }
    }

    public static void writeStringln(String s) {
        writeString(s);
        lineBreak();
    }

    public static void writeInt(int i) {
        writeString("" + i);
    }

    public static void writeIntln(int i) {
        writeString("" + i);
        lineBreak();
    }

    public static void writeBoolean(boolean b) {
        writeString("" + b);
    }

    public static void writeBooleanln(boolean b) {
        writeString("" + b);
        lineBreak();
    }

    public static void writeDouble(double d) {
        writeString("" + d);
    }

    public static void writeDoubleln(double d) {
        writeDouble(d);
        lineBreak();
    }

    public static void writeChar(char c) {
        writeString("" + c);
    }

    public static void writeCharln(char c) {
        writeChar(c);
        lineBreak();
    }

    public static void lineBreak() {
        try {
            System.out.println();
        } catch (Exception ex) {
            exceptionHandler(ex);
        }
    }

    protected static void exceptionHandler(Exception ex) {
        TerminalException err = new TerminalException(ex);
        throw err;
    }

    public static void writeException(Throwable ex) {
        writeString(ex.toString());
        ex.printStackTrace(System.err);
    }
}

class TerminalException extends RuntimeException {
    Exception ex;

    TerminalException(Exception e) {
        ex = e;
    }
}