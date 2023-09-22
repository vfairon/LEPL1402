public class Arrays{
    static void change_second_item(int[] a){
        a[2] = 6;
    }
    public static void main(String[] args) {
        //Test to see if it copies the object or reference when talking about lists
        int[] a = new int[4];
        int[] b = a;
        a[2] = 2;
        System.out.println("While changing directly about b, the value has changed : " + b[2]);

        // Now that we have seen it is the reference that is copied we can actually do this :
        change_second_item(a);
        System.out.println("Because the reference and not the variable is copied, the value has changed to : " + b[2]);
    }
}