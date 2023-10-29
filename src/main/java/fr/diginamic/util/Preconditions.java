package fr.diginamic.util;

public class Preconditions {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean isNumeric(String value) {
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean withinRange(int value, int min, int max) {
        return (value >= 1 && value <= 9);
    }

    public static boolean isMax(String value, int max) {
        return value.length() <= max;
    }
}
