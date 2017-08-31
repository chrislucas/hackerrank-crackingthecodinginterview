package com.br.xplor.regex;

/**
 * Created by r028367 on 31/08/2017.
 */
public class ComplexPassword {


    /**
     * Start of String and End of String Anchors
     * http://www.regular-expressions.info/anchors.html
     * */


    /*
    * Lookahead and Lookbehind Zero-Length Assertions
    * http://www.regular-expressions.info/lookaround.html
    * (?=) lookahead
    * */


    public static boolean verify (String pwd) {
        String regex = "^(?=.*[0-9])$";
        return pwd.matches(regex);
    }


    public static void main(String[] args) {

        String pwds [] = {
            "teste123456"
            ,"123456"
        };

        for(String pwd : pwds) {
            System.out.println(verify(pwd));
        }

    }

}
