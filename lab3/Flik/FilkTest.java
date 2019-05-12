import static org.junit.Assert.*;
import org.junit.Test;


public class FilkTest {
    @Test
    public void testIsSameNumber() {
        int a = 1, b = 0;
        boolean exp = false;
        assertTrue(Flik.isSameNumber(a,b) == exp);

        a = 128; b = 128;
        exp = true;
        assertTrue(Flik.isSameNumber(a,b) == exp);


    }

}
