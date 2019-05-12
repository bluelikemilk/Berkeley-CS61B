public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> D = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            D.addLast(word.charAt(i));
        }
        return D;

    }

    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.size() < 2) { // if all char pass the check
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (first == last) {
            return isPalindrome(deque);
        }
        // if first != last, return false
        return false;



    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        if (length == 0 || length == 1) { // always palindrome
            return true;
        } else {
            Palindrome palindrome = new Palindrome();
            Deque<Character> deque = palindrome.wordToDeque(word);
            return isPalindrome(deque);
        }

    }

    // this overload is off by one palindrome
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        if (length < 2) {
            return true;
        }
        int start = 0, end = length- 1;
        while(start < end) {
            if (!cc.equalChars(word.charAt(start), word.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;

    }
}
