package com.BarkalovCalculator;

/*Implementations of the following operators:
    + ,-,*,/
*/

public class Operator {
    public static final String operands= "-+/*";
    public final char Symbol;

    //Creating a operator
    public Operator(final String strOperator) {
        if (isOperator(strOperator) && strOperator.length()==1){
            this.Symbol = strOperator.charAt(0);
        } else {
            throw new IllegalArgumentException(strOperator + " is not operator");
        }
    }

    //Checks whether the string is an operator
    public static boolean isOperator(String arg){
        return arg.matches("["+operands+"]+");
    }

    //Calling the operator method
    public CalcNumber calc(final CalcNumber arg1, final CalcNumber arg2){
        final CalcNumber result;
        switch (this.Symbol){
            case '+': result= add(arg1, arg2);  break;
            case '-': result= less(arg1, arg2); break;
            case '/': result= split(arg1, arg2); break;
            case '*': result= multiply(arg1, arg2); break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.Symbol);
        }
        return result;
    }

    // Realizations operators
    public CalcNumber add(final CalcNumber arg1, final CalcNumber arg2) {
        int valTemp =arg1.value+arg2.value;
        return operations(arg1,arg2,valTemp);
    }
    public CalcNumber less(final CalcNumber arg1, final CalcNumber arg2) {
        int valTemp =arg1.value-arg2.value;
        return operations(arg1,arg2,valTemp);
    }
    public CalcNumber split(final CalcNumber arg1, final CalcNumber arg2) {
        int valTemp =arg1.value/arg2.value;
        return operations(arg1,arg2,valTemp);
    }
    public CalcNumber multiply(final CalcNumber arg1, final CalcNumber arg2) {
        int valTemp =arg1.value*arg2.value;
        return operations(arg1,arg2,valTemp);
    }

    //Creating a new operand object
    private CalcNumber operations (final CalcNumber arg1, final CalcNumber arg2, final int tempVal){
        CalcNumber result;
        if (!(arg1.Type == arg2.Type)) throw new IllegalArgumentException("Operand types don't match");
        if (arg1.Type == CalcNumber.Numeral_system.Arabic) {
            result = new CalcNumber(tempVal);
        } else {
            result = new CalcNumber(CalcNumber.arabic_to_roman(tempVal));
        }
        return result;
    }
}
