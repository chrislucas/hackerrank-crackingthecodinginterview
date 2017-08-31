package com.br.xplor.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by r028367 on 31/08/2017.
 * http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
 */
public class XplorerRegexQuantifiersAndMetachars {


    /**
     * Quantificador 'zero ou mais'
     * (*) == {0, } corresponde com zero ou mais elementos que se quer buscar
     * */
    public static void zeroOrMoreQuantifier() {
        /**
         * 1 ou mais caracteres
         * seguido de 0 ou mais espacos
         * seguido de 1 ou mais digitos
         * */
        String regex = "\\w+\\s*\\d+"; // Meta chars \w == [a-zA-Z_0-9] ; "[a-zA-Z_0-9]+\\s*\\d+"
        String [] array = {
             "teste 1"
            ,"teste 1011 teste"
            ,"teste 1011"
            ,"1011 teste"
            ,"1011 1111"
            ,"10111111"
            ,"10111111a"    // falso
        };
        for (String str : array) {
            // http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax
            /**
             * O uso do $ serve para indicar a posicao do argumento no metodo printf
             * 1$ indica o argumento 1 n$ o argumento n
             * */
            String fmt = String.format("'%%s' %%%1$ds\n", 30 - str.length());
            System.out.printf(fmt, str, Pattern.matches(regex, str));
        }

        return;
    }

    /**
     * Quantificador '1 ou mais'
     * + == {1, }
     * */

    /**
     * Quantificador 'dot'
     *
     * Quantificador que combina com um texto com 1 unico caracterer
     * menos o '\n'
     * */

    public static void dotQuantifier() {
        // String regex = ".+\\s";     // um caracter ou mais seguido de 1 espaco
        // String regex = ".+\\s*";     // um caracter ou mais seguido de 0 ou n espacos
        //String regex = ".+\\s+";     // um caracter ou mais seguido de 1 ou n espacos
        //String regex = "\\w+\\s+";     // um caracter ou mais seguido de 1 ou n espacos

        /**
         * Modificador 'm'
         * Permite fazer busca em texto multilinhas
         * Isso nao funciona no java
         * https://stackoverflow.com/questions/7911362/dotall-for-string-matches/7911567#7911567
         * "\n".matches("(?s).")
         * */
        // 1 \n
        System.out.printf("'\\n'.mathces( '(?s).' ) 1\\n %s\n", "\n".matches("(?s)."));
        System.out.printf("'\\n\\n'.matches( '(?s).' ) 1\\ %s\n\n", "\n\n".matches("(?s)."));
        // 0 ou mais zn
        System.out.printf("'\\n\\n'.matches('(?s).*') 0 ou mais \\n %s\n", "\n\n".matches("(?s).*"));
        System.out.printf("''.matches( '(?s).*')  0 ou mais \\n %s\n\n", "".matches("(?s).*"));
        // 1 ou mais zn
        System.out.printf("'\\n\'.matches( '(?s).+' ) 1 ou mais \\n %s\n", "\n".matches("(?s).+"));
        System.out.printf("''.matches('(?s).+')  1 ou mais \\n %s\n\n", "".matches("(?s).+"));

        //String regex = "(?s).*[a-zA-Z]?.*";
        /**
         * Procurar por um ponto num texto de multilinhas
         * */
        //String regex = "(?s).*\\..*";
        /**
         * Procurar 0 ou mais ocorrencias das palavras teste e Teste
         * numa string com multiplas linhas
         * */
        //String regex = "(?s).*[t|T]este.*";
        /**
         *
         * */
        String regex = "(?s).*[t|T]este.+";
        String array [] = {
             "Teste 1"
            ,"1\nteste\n\n"
            ,"1\nteste\n\nTeste"
            ,"1231321321"
            ,"1231321321"
            ,"1"
            ,"a"
            ,"aa"
            ,"aa "
            ,"22 "
            ,"a2 "
            ,"aa2 "
            ,"\na "
            ,"a "
            ,"\n\n\nteste "
            ,"\n\n\n. "
            ,". "
        };
        for (String str : array) {
            System.out.printf("'%s' %s\n", str.replaceAll("\n", "*"), Pattern.matches(regex, str));
        }
    }

    /**
     *
     * Quantificador chave {x,y}
     * {x, y} ocorre ao menos x vezes e no maximo y
     * {x, } ocorre ao menos X vezes
     * {x} ocorre exatamente X vezes
     * */
    public static void bracketQuantifier() {

    }

    /**
     * Quantificador ? == {0, 1}
     * Serve para verificar a nenhuma ou no maximo 1 ocorrencia
     * do padrao procurado
     * */
    public static void questionQuantifier() {

    }

    /**
     * Quantificador dotQuestion .?
     *
     * Procura por pelo menos uma ocorrencia do padrao procurado
     * . Quando a primeira ocorrencia for encontrada a busca termina
     * Sempre usado depois de um outro quantificador
     * Exemplo:
     *  "aa99aa".matches("[a-z].+\\d+.*?") == true
     *  "[a-z].+\\d+.*?" :
     *  1) Ocorrencia de 1 ou mais caracteres de a-z minusculo
     *  2) Seguido 1 ou mais digitos
     *  3) O quantificador .*?
     *
     * */

    public static void dotQuestionQuantifier() {
        String regex = "\\w{1}\\d+?";
        System.out.println("a9".matches(regex));
        regex = ".+\\d.*?";
        System.out.println("a9".matches(regex));

        // CASE_INSENTIVIVE = (?i);
        regex = "(?i)[a-c].*?";
        System.out.println("AAA9".matches(regex));      // true
        regex = "[a-c].*?";
        System.out.println("AAA9".matches(regex));      // false
        regex = "[a-cA-V].*?";
        System.out.println("AAA9".matches(regex));      // true
        regex = "[a-cA-V].*?";
        System.out.println("AAA9\nAAAA".matches(regex));  // false
        // CASE INSENSITIVE e (?s) 'single line mode' corresponde com \n
        regex = "(?is).+[a-c].*?";
        System.out.println("AAA9\nAAAA".matches(regex));
    }

    public static void questionQuantifierMakeOptional() {
        String regex = "\\w{1}(\\d{3})?";       // faz o que esta entre parenteses opcional
        System.out.println("a".matches(regex));
        System.out.println("teste 123321".matches("\\bteste\\b"));
        System.out.println("t".matches("\\b(\\w)"));
        System.out.println("teste654654".matches("[a-zA-Z0-9_]+"));
        System.out.println("Teste 123".matches("\\bTeste\\b"));
    }


    public static void wordBoundaryMetachar() {
        Pattern pattern = Pattern.compile("\\bteste\\b");
        pattern = Pattern.compile("\\bteste");
        Matcher matcher = pattern.matcher("asdasdsa\nteste aabbb teste basbab teste\nteste");
        while (matcher.find()) {
            System.out.printf("Start(%d) End(%d)\n", matcher.start(), matcher.end());
        }
    }

    public static void flagFormatString() {
        String format = String.format("%%%1$ds\n%%%2$ds", 10, 20);
        System.out.printf(format, "Teste", "Outro teste");
    }

    public static void dotQuantifier2() {

        String regex = "A.B.C.D.";
/*
        System.out.println(".".matches(regex));
        System.out.println("A+B-C=DE".matches(regex));
        System.out.println("a".matches(regex));
*/
        // https://www.hackerrank.com/challenges/matching-anything-but-new-line
        // DONE
        /*
        regex = ".{3}\\..{3}\\..{3}\\..{3}";
        //regex = "(?s).*.{3}\\..{3}\\..{3}\\..{3}";
        System.out.println("abc.efg.hij.klm".matches(regex));
        System.out.println("....efg.hij.klm".matches(regex));
        System.out.println("........hij.klm".matches(regex));
        System.out.println("............klm".matches(regex));
        System.out.println("...............".matches(regex));
        System.out.println("abc-efg.hij.klm".matches(regex));
        System.out.println("ab.efg.hij.klm".matches(regex));
        System.out.println("123.efg.hij.klm".matches(regex));
        System.out.println("???.efg.hij.klm".matches(regex));
        System.out.println("###.efg.hij.klm".matches(regex));
        System.out.println("###.efga.hija.klm".matches(regex));
        System.out.println("###.efg.hija.klm".matches(regex));
        System.out.println("###.efg.hij.kalm".matches(regex));
        System.out.println("\n\n\n.efg.hij.klm".matches(regex));
*/
        System.out.println("9999".matches("\\d{3}"));
        System.out.println("aaa999".matches("\\d{3}.*?"));
        System.out.println("9aa".matches("\\d*.*?"));
        System.out.println("aa99aa".matches(".*\\d+.*?"));
        System.out.println("aa99aa".matches("[a-z].+\\d+.*?"));
        System.out.println("aa99aa".matches("[a-z].+\\d+.+?"));
        System.out.println("aaaa".matches("[a-z].+\\d+.+?"));
    }




    public static void main(String[] args) {
        //zeroOrMoreQuantifier();
        //dotQuantifier();
        //dotQuantifier2();
        //dotQuestionQuantifier();

        questionQuantifierMakeOptional();
        //wordBoundaryMetachar();

    }

}
