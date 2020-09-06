package com.BarkalovCalculator;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CalcNumber {
    /**
     * A constant holding the minimum value an {@code int} can
     * have, -2<sup>31</sup>.
     */
    public static  int MIN_VALUE = 0x80000000;
    /**
     * A constant holding the maximum value an {@code int} can
     * have, 2<sup>31</sup>-1.
     */
    public static  int MAX_VALUE = 0x7fffffff;

    public final Integer value;//Numeric representation of a number

    public static enum Numeral_system {Roman, Arabic};
    public final Numeral_system Type;
    public static LinkedHashMap<String, Integer> RomanNumbers;
    static {
        RomanNumbers = new LinkedHashMap<String, Integer>();
        RomanNumbers.put("M", 1000);
        RomanNumbers.put("CM", 900);
        RomanNumbers.put("D", 500);
        RomanNumbers.put("CD", 400);
        RomanNumbers.put("C", 100);
        RomanNumbers.put("XC", 90);
        RomanNumbers.put("L", 50);
        RomanNumbers.put("XL", 40);
        RomanNumbers.put("X", 10);
        RomanNumbers.put("IX", 9);
        RomanNumbers.put("V", 5);
        RomanNumbers.put("IV", 4);
        RomanNumbers.put("I", 1);
        RomanNumbers.put("~", 0);

    }
    //Class constructors
    public CalcNumber(final String strValue) {
        if (isRoman(strValue)) {
            this.Type = Numeral_system.Roman;
            this.value = roman_to_arabic(strValue);
        } else {
            if (isArabic(strValue)) {
                this.Type = Numeral_system.Arabic;
                this.value = Integer.parseInt(strValue);
            } else throw new IllegalArgumentException(strValue + " is not number");
        }
    }
    public CalcNumber(Number value) {
        if (isArabic(value.toString())) {
            this.Type = Numeral_system.Arabic;
            this.value = (int) value;
        } else throw new IllegalArgumentException(value.toString() + " is not integer number");
    }

    //Static method converting a Roman number to an Arabic number
    public static int roman_to_arabic(@NotNull final String strRoman) {
        int result = 0;
        final String strRoman_loc;
        if (strRoman.charAt(0) == '-') strRoman_loc = strRoman.substring(1);
        else strRoman_loc = strRoman;
        if (!strRoman.isEmpty())
            if (1 < strRoman_loc.length())
                for (byte index = 0; index < strRoman_loc.length(); index++) {
                    if (getNumber_arabic(strRoman_loc, index) < getNumber_arabic(strRoman_loc, index + 1)) {
                        result -= getNumber_arabic(strRoman_loc, index);
                    } else {
                        result += getNumber_arabic(strRoman_loc, index);
                    }
                }
            else {
                result += getNumber_arabic(strRoman_loc, 0);
            }
        if ((result <= MIN_VALUE) || (result > MAX_VALUE))
            throw new IllegalArgumentException(result + " is not in range");
        if (strRoman.charAt(0) == '-') result *= -1;
        return result;
    }
    //A private method that converts a single character of a Roman number to an Arabic number
    private static int getNumber_arabic(final String strRoman, final int index) {
         if (strRoman.length() > index) {
            return RomanNumbers.get(String.valueOf(strRoman.charAt(index)));
        } else {
            return 0;
        }
    }
    //Static method for converting an Arabic number to a Roman number
    public static String arabic_to_roman(final int intArabic) {
        if ((intArabic <= MIN_VALUE) || (intArabic > MAX_VALUE)) throw new IllegalArgumentException(intArabic + " is not in range");
        int intArabic_loc = intArabic;
        if (intArabic < 0) intArabic_loc = intArabic * (-1);  else intArabic_loc = intArabic;
        StringBuilder sb = new StringBuilder();
        if (!(intArabic == 0)) {
            while (intArabic_loc >= 1)
                for (HashMap.Entry<String, Integer> currentSymbol : RomanNumbers.entrySet())
                    if (currentSymbol.getValue() <= intArabic_loc) {
                        sb.append(currentSymbol.getKey());
                        intArabic_loc -= currentSymbol.getValue();
                        break;
                    }
        } else sb.append('~');
        if (intArabic < 0) sb.insert(0, '-');
        return sb.toString();
    }
    //A static method that checks whether a string is a Roman number
    public static boolean isRoman(final String strVal) {
        return strVal.matches("[-~IVXLCDM]+");
    }
    //A static method that checks whether a string is an Arabic number
    public static boolean isArabic(final String strVal) {
        return strVal.matches("[-\\d]+");
    }

    @Override
    public String toString() {
        if (this.Type == Numeral_system.Arabic) return this.value.toString();
        else return arabic_to_roman(this.value);
    }
}
