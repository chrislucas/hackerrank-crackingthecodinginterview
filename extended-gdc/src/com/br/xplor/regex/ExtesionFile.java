package com.br.xplor.regex;

/**
 * Created by r028367 on 11/09/2017.
 *
 * Estudar as regex desse site
 * https://stackoverflow.com/questions/374930/validating-file-types-by-regular-expression
 *
 */
public class ExtesionFile {

    /**
     * ^           = beginning of string
     * .+          = at least one character (any character)
     * \.          = dot ('.')
     * (?:pattern) = match the pattern without storing the match)
     * [dD]        = any character in the set ('d' or 'D')
     * [xX]?       = any character in the set or none
     * ('x' may be missing so 'doc' or 'docx' are both accepted)
     * |           = either the previous or the next pattern
     * $           = end of matched string
     *
     * (.*?)\.(jpg|gif|doc|pdf)$
     *
     * Quantificador ? == {0, 1}
     * */
    public static boolean isBMPFile(String filename) {
        /**
         *
         * * {0, } quantificador de 0 <= ocorrencias < INF
         *
         * */
        String regex = "^.*\\.bmp";
        return filename.matches(regex);
    }


    public static void main(String[] args) {
        System.out.println(isBMPFile("teste.bmp"));
    }

}
