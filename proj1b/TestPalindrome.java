import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome() {
        String s1 = "racecar";
        assertTrue(palindrome.isPalindrome(s1));
        s1 = "root";
        assertFalse(palindrome.isPalindrome(s1));
        s1 = "a";
        assertTrue(palindrome.isPalindrome(s1));
        s1 = "";
        assertTrue(palindrome.isPalindrome(s1));
        s1 = "cat";
        assertFalse(palindrome.isPalindrome(s1));
        s1 = "abbc";
        assertFalse(palindrome.isPalindrome(s1));
    }

    @Test
    public void isPalindromeOffByOne() { // overload isPalindrome with off by one
        OffByOne offByOne = new OffByOne();
        String s1 = "flake";
        assertTrue(palindrome.isPalindrome(s1, offByOne));
        s1 = "toot";
        assertFalse(palindrome.isPalindrome(s1, offByOne));
        s1 = "a";
        assertTrue(palindrome.isPalindrome(s1, offByOne));
        s1 = "";
        assertTrue(palindrome.isPalindrome(s1, offByOne));
        s1 = "cat";
        assertFalse(palindrome.isPalindrome(s1, offByOne));
        s1 = "abcb";
        assertTrue(palindrome.isPalindrome(s1, offByOne));
    }
}
