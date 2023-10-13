package complexity;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Grade
public class MaximumSumSubarrayTest {
    
    
    @Test
    @Grade(value = 1)
    public void testSimple() {
        int [] array = new int[]{100,100,-1,-197,-1,400,10,13,-2};
        
        MaximumSumSubarray.ArrayIndex solution = MaximumSumSubarray.maximumSumSubarray(array);
        assertEquals(5, solution.start);
        assertEquals(7, solution.end);
    }
    
    
}
