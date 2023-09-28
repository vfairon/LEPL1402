public class Solution_scope_variable_1 {

    static int a = 3;

    static void increment(int a) {
        Solution_scope_variable_1.a += 5;   // we want the class variable!
    }

    public static void main(String[] args) {
        increment(10);
        System.out.println(a);
    }
}