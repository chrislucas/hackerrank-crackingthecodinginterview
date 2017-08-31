package com.br.xplor.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by r028367 on 29/08/2017.
 */
public class XploreRegex {


    /**
     * http://tutorials.jenkov.com/java-regex/pattern.html
     * */

    private static boolean isRegisterIcomon(CharSequence str) {
        /**
         * Comeca com r | R
         * seguido de zero
         * seguido de 1 ou mais numeros
         * + == {1, }
         * */
        //String regex = "^(r|R)0\\d+";
        /**
         * Comeca com r|R
         * seguido de 1 '0'
         * seguido de 5 digitos ou mais
         * */
        String regex = "^(r|R)0\\d{5,}";
        /**
         * Comeca com r|R
         * seguido do caracter '0'
         * seguido de 1 ou mais digitos
         * seguido de 1 ou mais letras
         * */
        //String regex = "^(r|R)0\\d+[a-zA-Z]+";

        //Pattern pattern = Pattern.compile(regex);
        //Matcher matcher = pattern.matcher(str);
        //System.out.println(matcher.matches());
        //System.out.println(Pattern.matches(regex, str));
        //((String) str).matches(regex)
        return Pattern.matches(regex, str);
    }

    public static void main(String[] args) {
        String array [] = {
             "r028367"
            ,"R028367"
            ,"R028367AZ"
            ,"s028367"
            ,"r8367"
            ,"r08367"
            ,"R8367"
            ,"R08367"
            ,"R008367"
            ,"028367r"
            ,"r 002837"
            ,"r 0 0 2 8 3 7"
            ,"r028367qualquerdepois"
            ,"um palavrão qualquer"
            ,"0"
            ,"r"
            ,"R"
        };

        for(String str : array) {
            System.out.printf("String: '%s' %s\n", str, isRegisterIcomon(str) ? "Matches" : "NO Matches");
        }

    }


}
