public class Node {

    private boolean isWord;
    private Node[] children;

    public Node() {
        isWord = false;
        children = new Node[26];
    }

    public boolean isWord() {
        return isWord;
    }

    // We probably should throw or catch an exception here.
    public Node child(char ch) {
        int index = (int) ch - (int) 'a';
        return children[index];
    }

    public void setTrue() {
        isWord = true;
    }

    public void setFalse() {
        isWord = false;
    }



}
