public class Node {

    private boolean isWord;
    private Node[] children;

    public Node() {
        isWord = false;
        children = new Node[26];
    }

    public Node(boolean isTrue) {
        isWord = isTrue;
        children = new Node[26];
    }

    public boolean isWord() {
        return isWord;
    }

    // We probably should throw or catch an exception here.
    public Node getChild(char ch) {
        ch = Character.toLowerCase(ch);
        int index = (int) ch - (int) 'a';
        return children[index];
    }

    public void setChild(char ch, Node node) {
        ch = Character.toLowerCase(ch);
        int index = (int) ch - (int) 'a';
        children[index] = node;
    }

    public void setTrue() {
        isWord = true;
    }

    public void setFalse() {
        isWord = false;
    }

    public boolean isEnd() {
        return isWord;
    }



}
