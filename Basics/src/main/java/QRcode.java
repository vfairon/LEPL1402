package basics;

import java.util.Arrays;

/**
 * Make sure that the equal method consider
 * two QR codes as identical if they have been flipped
 * by 0,1,2 or 3 quarter rotations
 *
 * For instance the two following matrices should be
 * considered equals if you flip your head by 180 degrees
 *
 *         boolean [][] t0 = new boolean[][] {
 *                 {false,true,false,false},
 *                 {false,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,true}
 *         };
 *
 *         // t2 is a version of t2 with two quarter rotations
 *         boolean [][] t2 = new boolean[][] {
 *                 {true,false,true,true},
 *                 {true,false,false,true},
 *                 {true,true,false,false},
 *                 {false,false,true,false}
 *         };
 */
public class QRcode {

    protected boolean [][] data;

    /**
     * Create a QR code object
     * @param data is a n x n square matrix
     */
    public QRcode(boolean [][] data) {
        this.data = data;
    }

    private void rotate90() {
        int n = data.length;
        boolean[][] rotatedData = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotatedData[i][j] = this.data[n - 1 - j][i];
            }
        }
        this.data = rotatedData;
    }

    /**
     * Return true if the other matrix is identical up to
     * 0, 1, 2 or 3 number of rotations
     * @param o the other matrix to compare to
     * @return
     */
    @Override
    public boolean equals(Object o) {
        QRcode otherQRCode = (QRcode) o;// casting wtf have to reaserch that
        boolean res = false;
        boolean intermediate_eval = false;
        for (int rotation = 0; rotation < 4; rotation++) {
            boolean intermediate = true;

            for (int i = 0; i < otherQRCode.data.length; i++) {
                for (int j = 0; j < otherQRCode.data.length; j++) {
                    if (otherQRCode.data[i][j] !=  this.data[i][j]){
                        intermediate = false;
                    }
                }
            }
            if (intermediate){
                return true;
            }
            otherQRCode.rotate90();
        }
        return false;
    }
}
