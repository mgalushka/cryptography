package com.maximgalushka.cryptography;

/**
 * @since 7/10/2014.
 */
public final class Tools {

    public static char[] chars(String encoded) {
        int L = encoded.length();
        char[] result = new char[L / 2];
        for (int i = 0; i < L; i += 2) {
            String digits = encoded.substring(i, i + 2);
            char val = (char) Integer.parseInt(digits, 16);
            result[i / 2] = val;
        }
        return result;
    }

    public static byte[] bytes(String encoded) {
        int L = encoded.length();
        byte[] result = new byte[L / 2];
        for (int i = 0; i < L; i += 2) {
            String digits = encoded.substring(i, i + 2);
            byte val = (byte) Integer.parseInt(digits, 16);
            result[i / 2] = val;
        }
        return result;
    }

    public static String encoded(byte[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (byte c : string) {
            sb.append("");
            sb.append(Integer.toHexString(c & 0xFF));
            sb.append("\t");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String encoded0(byte[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (byte c : string) {
            sb.append("");
            String r = Integer.toHexString(c & 0xFF);
            if (r.length() == 1) r = "0" + r;
            sb.append(r);
            sb.append("");
        }
        sb.append("]");
        return sb.toString();
    }

    public static char xor(char a, char b) {
        return (char) (a ^ b);
    }

    public static char[] xor(char[] a, char[] b) {
        int min = Math.min(a.length, b.length);
        char[] result = new char[min];
        for (int i = 0; i < min; i++) {
            result[i] = xor(a[i], b[i]);
        }
        return result;
    }

    public static byte xor(byte a, byte b) {
        return (byte) (a ^ b);
    }

    public static byte[] xor(byte[] a, byte[] b) {
        int min = Math.min(a.length, b.length);
        byte[] result = new byte[min];
        for (int i = 0; i < min; i++) {
            result[i] = xor(a[i], b[i]);
        }
        return result;
    }

    public static String printSpaces(char[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (char c : string) {
            sb.append("");
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                sb.append(String.valueOf(c));
            } else {
                sb.append(" ");
            }
            sb.append("\t");
        }
        sb.append("");
        return sb.toString();
    }

    public static String printSpaces(byte[] string) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (byte c : string) {
            sb.append("");
            if (c >= 32 && c <= 126) {
                sb.append(String.valueOf((char) c));
            } else {
                sb.append(" ");
            }
            sb.append("");
        }
        sb.append("");
        return sb.toString();
    }
}
