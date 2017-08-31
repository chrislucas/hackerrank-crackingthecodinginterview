package com.br.xplor.regex.problems;

/**
 * Created by r028367 on 31/08/2017.
 * https://www.hackerrank.com/challenges/matching-digits-non-digit-character?h_r=next-challenge&h_v=zen
 * DONE
 */
public class MatchDigitsAndNonDigits {

    public static void main(String[] args) {
        String regex = "([a-z]{2}[A-Z]{1}){3}";
        /*
        System.out.println("xxXxxXxxX".matches(regex));
        System.out.println("xxXxxXxxXxxX".matches(regex));
        System.out.println("xxXxxX".matches(regex));
        System.out.println("XXxXXxXXx".matches(regex));
        System.out.println("aaBaaBaaB".matches(regex));
        System.out.println("acBdcEfgH".matches(regex));
        */
        //regex = "([0-9]{2}[^0-9]{1}){2}[0-9]{4}";
        regex = "([\\d]{2}[\\D]{1}){2}[\\d]{4}";
        System.out.println("02A03B1234".matches(regex));
        System.out.println("02A1234".matches(regex));
        System.out.println("02a03b1234".matches(regex));
        System.out.println("02a03b123a".matches(regex));
        System.out.println("06-11-2015".matches(regex));
        System.out.println("10a10.2015452254".matches(regex));
        System.out.println("10a10.2015".matches(regex));
    }
}
