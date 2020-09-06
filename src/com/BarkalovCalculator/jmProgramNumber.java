package com.BarkalovCalculator;
/*
    Wrapper class of the CalcNumber on the basis of inadequate customer requirements
 */
public class jmProgramNumber  extends  CalcNumber implements jmProgram{

    public static int MIN_VALUE = jmProgram.MIN_VALUE;
    public static int MAX_VALUE = jmProgram.MAX_VALUE;

    public jmProgramNumber(String strValue) {
        super(strValue);

        if ((this.value>MAX_VALUE) || (this.value<MIN_VALUE)) throw new IllegalArgumentException(strValue + " Does not meet the conditions of the interface - jmProgram");
    }
    public jmProgramNumber(Number Value) {
        super(Value);
        if ((this.value>MAX_VALUE) || (this.value<MIN_VALUE)) throw new IllegalArgumentException(Value + " Does not meet the conditions of the interface - jmProgram");
    }
}
