public class Node {

    private boolean isWord;
    private char[] next;

    public Node() {
        isWord = false;
        next = new char[26];
    }

    public boolean isWord() {
        return isWord;
    }

    // We probably should throw or catch an exception here.
    public char next(char ch) {
        int index = (int) ch - (int) 'a';
        return next[index];
    }

    public void setTrue() {
        isWord = true;
    }

    public void setFalse() {
        isWord = false;
    }



}
