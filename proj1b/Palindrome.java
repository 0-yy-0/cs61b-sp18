public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> res = wordToDeque(word);
        return isPalindrome(res);
    }
    private boolean isPalindrome(Deque res) {
        if (res.size() <= 1) {
            return true;
        }
        if (res.removeFirst() == res.removeLast()) {
            return isPalindrome(res);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }



}
