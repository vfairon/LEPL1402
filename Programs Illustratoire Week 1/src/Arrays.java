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


        // Now Let's see an example of multi dimentionnal array :
            //Because it is actually 3 boxes that reference 7 each, it is still references. When the c[0] is copied to the
            // d[], it is a reference to the c[0] meaning that if one change, the other changes as well
        int[][] c = new int[3][5];
        int d[] = c[0];  // b is now a reference to an int array with 5 elements
        d[3] = 7;
        System.out.println(c[0][3]);  // b[3] and a[0][3] are the same element
    }
}