public class Palindrome {

    /** Returns an array of characters from String parameter word. */
    public Deque<Character> wordToDeque(String word) {
        Deque deque = new LinkedListDeque<>();
        char[] charArray = word.toCharArray();
        // from the internet, convert a string into an array of characters

        for (int i = 0; i < charArray.length; i++) {
            deque.addLast(charArray[i]);
        }

        return deque;
    }

    /** Returns parameter String word backwards. */
    public Deque<Character> reverseDeque(String word){
        Deque deque = new LinkedListDeque<>();
        char[] charArray = word.toCharArray();
        // from the internet, convert a string into an array of characters

        for (int i = 0; i < charArray.length; i++) {
            deque.addFirst(charArray[i]);
        }

        return deque;
    }

    /** Returns true if given word is a palindrome, false otherwise. */
    public boolean isPalindrome(String word) {
        if (word.isEmpty()) {
            return true;
        }


        Deque a = wordToDeque(word);
        Deque b = wordToDeque(word);

        for (int i = 0; i < word.length(); i++) {
            if (!a.removeFirst().equals(b.removeLast())){
                return false;
            }
        }

        return true;
    }

    /** Returns true if word is a off-by-one palindrome. */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> a = wordToDeque(word);
        Deque<Character> b = reverseDeque(word);

        for (int i = 0; i < word.length(); i++) {
            if (word.length() % 2 != 0) {
                if (i == word.length()/2){
                    a.removeFirst();
                    b.removeFirst();
                    continue;
                }
            }

            if (!cc.equalChars(a.removeFirst(),b.removeFirst())){
                return false;
            }
        }

        return true;
    }


}
