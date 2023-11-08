package basics;

public class CommonElements {
    /**
     *
     * @param tab1 is a non null array
     * @param tab2 is a non null array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {i such that tab1[i] == tab2[i]}
     *         for instance count([1,3,5,5],[1,2,5,5,6]) = 3
     */
    public static int count(int [] tab1, int [] tab2) {
        int num = 0;
        for (int i = 0; i < Math.min(tab1.length, tab2.length); i++){
            if (tab1[i] == tab2[i]){
                num += 1;
            }
        }
         return num;
    }

    /**
     *
     * @param tab1 is a non null 2D array
     * @param tab2 is a non null 2D array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {(i,j) such that tab1[i][j] == tab2[i][j]}
     */
    public static int count(int [][] tab1, int [][] tab2) {
        int num = 0;
        for (int i = 0; i < Math.min(tab1.length, tab2.length); i++){
            for (int j = 0; j <Math.min(tab1[0].length, tab2[0].length); j++){
                if (tab1[i][j] == tab2[i][j]){
                    num += 1;
                }
            }
        }
         return num;
    }
}
