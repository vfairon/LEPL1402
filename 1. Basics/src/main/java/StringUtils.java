package basics;

public class StringUtils {


    /**
     * Split a string according to a delimiter
     *
     * @param str The string to split
     * @param delimiter The delimiter
     * @return An array containing the substring which fall
     *          between two consecutive occurence of the delimiter.
     *          If there is no occurence of the delimiter, it should
     *          return an array of size 1 with the string at element 0
     */
    public static String [] split(String str, char delimiter){
        int count = 1;
        for (int i = 0; i<str.length();i++)
            if (str.charAt(i) == delimiter){
                count++;
            }

        String[] to_return = new String[count];
        StringBuilder temp = new StringBuilder("");
        int f = 0;

        if (count ==1){
            to_return[0] = str;
            return to_return;
        }


        for (int i = 0; i<str.length();i++) {

            if (str.charAt(i) == delimiter) {
                to_return[f] = temp.toString();
                System.out.println(temp.toString());
                temp = new StringBuilder("");
                f += 1;
            }
            else {
                temp.append(str.charAt(i));
            }
        }

        if (f < count){
            to_return[count-1] = temp.toString();
        }
         return to_return;
    }


    /**
     * Find the first occurence of a substring in a string
     *
     * @param str The string to look in
     * @param sub The string to look for
     * @return The index of the start of the first appearance of
     *          the substring in str or -1 if sub does not appear
     *          in str
     */
    public static int indexOf(String str, String sub){
        if (str.length() == sub.length()) {
            for (int i = 0; i < sub.length(); i++) {
                if (str.charAt(i) != sub.charAt(i)) {
                    return -1;

                }
                return 0;
            }
        }

        for (int i = 0; i < str.length() - sub.length()+1; i++){
            boolean istrue = true;

            for (int j = 0; j<sub.length(); j++){
                if (str.charAt(i+j) != sub.charAt(j)){
                    istrue = false;
                    break;

                }

            }
            if (istrue == true){
                return i;
            }
        }


         return -1;
    }


    /**
     * Convert a string to lowercase
     *
     * @param str The string to convert
     * @return A new string, same as str but with every
     *          character put to lower case.
     */
    public static String toLowerCase(String str){
         return str.toLowerCase();
    }


    /**
     * Check if a string is a palyndrome
     *
     * A palyndrome is a sequence of character that is the
     * same when read from left to right and from right to
     * left.
     *
     * @param str The string to check
     * @return true if str is a palyndrome, false otherwise
     */

    public static boolean palindrome(String str){

        for (int i = 0; i < str.length() ; i++) {
            if (str.charAt(i) != str.charAt(str.length()-1-i)){
                return false;
            }

        }
         return true;
    }


}