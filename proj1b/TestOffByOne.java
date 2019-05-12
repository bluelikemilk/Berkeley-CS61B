import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() { // test equal chars off by one
        char c1 = 'a';
        char c2 = 'b';
        assertTrue(offByOne.equalChars(c1, c2));

        c1 = 'b';
        assertFalse(offByOne.equalChars(c1, c2));

        c1 = 'r';
        c2 = 'q';
        assertTrue(offByOne.equalChars(c1, c2));
    }
}
