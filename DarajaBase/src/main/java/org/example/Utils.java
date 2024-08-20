package org.example;

public class Utils {
    public static boolean isSuccessfulStatusCode(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
}
