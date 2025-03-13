package com.pideep.tinyurl.utils;

public class Base62Encoder {
    //encode function
    //decode function
    private static final String alpha = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int Base = alpha.length();

    public static String encode(long input) {
        if (input <= 0) {
            return String.valueOf(alpha.charAt(0));
        }

        StringBuilder encoded = new StringBuilder();

        while(input > 0) {
            encoded.insert(0, alpha.charAt((int) (input%Base)));
            input /= Base;
        }
        return encoded.toString();
    }

    public static long decode(String input) {
        long decoded = 0;
        for(char c: input.toCharArray()) {
            decoded = decoded*Base + alpha.indexOf(c);
        }
        return decoded;
    }
}



