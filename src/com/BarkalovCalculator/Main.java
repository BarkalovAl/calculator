package com.BarkalovCalculator;
//https://github.com/BarkalovAl/calculator.git

import java.util.Scanner;
public class Main{
     public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      String t= in.nextLine();
      CalcParser Calc = new CalcParser(t);
      System.out.println(Calc.result.toString());
}}