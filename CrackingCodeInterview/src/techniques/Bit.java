package techniques;

/**
 * Created by C_Luc on 04/08/2017.
 */
public class Bit {

    public static int getRepeat(int [] array) {
        int rep = 0;
        for(int x : array) {
            rep ^= x;
        }
        return rep;
    }


    public static void test() {
        int [][] m = {{}, {}};
        System.out.println(getRepeat(m[0]));
    }


    public static void main(String[] args) {
        test();
    }

}
