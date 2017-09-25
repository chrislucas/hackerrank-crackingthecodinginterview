package com.br.xplor.regex;

/**
 * Created by r028367 on 01/09/2017.
 *
 * http://www.regular-expressions.info/charclass.html
 */
public class CharSetRegx {

    /**
     * Com o "character set|class ([])" podemos dizer para engine regex
     * verificar se uma string combina com 1 ou mais caracteres que estao dentro de []
     *
     * Exemplo
     * gr[ae]y combina com "gray" e "grey"
     * porem nao combina com "graay" e "greey", pos da forma que foi escrito o
     * motor regex esta procurando por incidencia de 1 a ou 1 e
     *
     * */

    public static void charSetRegex() {
    /*
        System.out.println("Christoffer".matches("[c|C]hristoffer"));
        System.out.println("christoffer".matches("[c|C]hristoffer"));
        System.out.println("christoffer".matches("[C]hristoffer"));
        System.out.println("christoffer".matches("[c]hristoffer"));
        System.out.println("Hello World".matches("\\w+[o\\sWorl]\\w+"));
        System.out.println("Hello World".matches("\\w+[o\\sw]\\w+"));
    */
        System.out.println("gray".matches("gr[ae]y"));      // true
        System.out.println("grey".matches("gr[ae]y"));      // true
        System.out.println("greey".matches("gr[ae]y"));     // false
        System.out.println("graay".matches("gr[ae]y"));     // false
        System.out.println("graay".matches("gr[ae]+y"));    // true
        System.out.println("greey".matches("gr[ae]+y"));    // true
    }

    /**
     * Negate Char Class (^)
     * [^regex], funciona como uma negacao a regex escrita a frente do simbolo
     * Exemplo
     * q[^u] : corresponde com uma string que tenha a letra que 'q' que é seguida
     * de um outro char que nao a letra 'u'
     * */
    public static void negCharSetRegex() {
        System.out.println("teste teste\n".matches("[^0-9]+"));                     // true
        System.out.println("teste teste\n".matches("[^0-9\r]+"));                   // true
        System.out.println("teste teste\r".matches("[^0-9\r]+"));                   // false
        // verificar num texto de multiplas linhas se nao existe nenhum numero
        System.out.println("teste teste\nteste\r".matches("(?s).*[^0-9]"));         // true
        System.out.println("teste teste\nteste\r".matches("(?s).*[^0-9\n\r]"));     // false
        //
        System.out.println("132321.321321.1321212".matches("[(^a-zA-Z)+\\.]"));
        System.out.println("132321 321321 1321212".matches("[^a-zA-Z\\.]+"));
        System.out.println("132321 321321 1321212".matches("[^a-zA-Z\\.\\s]+"));
        System.out.println("1323-21321321-1321212".matches("[^a-zA-Z\\.\\s]+"));
        System.out.println("teste 123321".matches("[^0-9]+"));
    }

    public static void negCharSetRegex2() {
        /**
         * "q[^u]\\w*" combina com um conjunto de caracter que comece com
         * a letra 'q' e o proximo caracter nao seja 'u', e seja seguido de
         * 0 ou mais caracteres
         * */
        System.out.println("qwerty".matches("(q|Q)[^u]\\w*"));
        System.out.println("Qwerty".matches("(q|Q)[^u]\\w*"));
        System.out.println("Iraq and Syria".matches(".+(q|Q)[^u].+"));
        System.out.println("IraQ and Syria".matches(".+(q|Q)[^u].+"));
        System.out.println("IraQ and Syria".matches(".+q[^u].+"));          // false
        System.out.println("IraQuand Syria".matches(".+(q|Q)[^u].+"));      // false
        System.out.println("IraQ+and Syria".matches(".+(q|Q)[^u].+"));      // true
    }


    /**
     *
     * */
    public static void negLookAhead() {

    }


    public static void main(String[] args) {
        //charSetRegex();
        //negCharSetRegex();
        negCharSetRegex2();
    }

}
