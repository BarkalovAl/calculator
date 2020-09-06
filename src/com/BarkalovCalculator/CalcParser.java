package com.BarkalovCalculator;
import java.util.StringTokenizer;
/*
    The cash register is just a demonstration, and the jmProgram interface is also used.
*/
@Deprecated
public class CalcParser  implements jmProgram {
    public  CalcNumber result;
    CalcParser(String val) {
        StringTokenizer st = new StringTokenizer(val.replaceAll(" ",""), Operator.operands, true);
        CalcNumber arg = new jmProgramNumber(st.nextToken());
        Operator operator = null;
        int argIndex=1;
        while (st.hasMoreTokens()) {
            operator = new Operator(st.nextToken());
            arg = operator.calc(arg, new jmProgramNumber(st.nextToken()));
            argIndex+=1;
            if(argIndex>MAX_ARG) throw new IllegalArgumentException(val+" Does not meet the conditions of the interface - jmProgram");
        }
         this.result = arg;
    }
}
