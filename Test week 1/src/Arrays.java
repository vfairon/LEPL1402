public class Arrays{
    public static void main(String[] args) {
        //Test to see if it copies the object or reference when talking about lists
        int[] a = new int[4];
        int[] b = a;
        a[2] = 2;
        System.out.println("While changing directly about b, the value has changed : " + b[2]);
    }
}