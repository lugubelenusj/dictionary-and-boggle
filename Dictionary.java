import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dictionary {

    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // Have to rename this or the parameters.
    private Node root;

    /**
     * Constructor that creates an empty dictionary.
     */
    public Dictionary() {
        root = new Node();
    }

    /**
     * Constructor that reads in a list of words from a file,
     * and then adds it to the dictionary.Assumes that the 
     * file has only one word per line.
     */
    public Dictionary(String fileName) throws IOException {
        root = new Node();
        Path file = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(file, Charset.forName("UTF-8"));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                add(line.trim());
            }
        }
    }

    /**
     * Adds a word into the dictionary.
     * 
     * @param word
     *      the word to add to the dictionary
     */
    public void add(String word) {
        if (word.length() <= 0) {
            System.err.println("add(): Do not enter an empty string.");
        }
        else {
            add(word.toLowerCase(), root);
        }
    }

    private void add(String word, Node tree) {
        char first = word.charAt(0);

        if (word.length() == 1) {
            tree.setChild(first, new Node(true));
        }

        else {
            if (tree.getChild(first) == null) {
                tree.setChild(first, new Node());
            }
            add(word.substring(1), tree.getChild(first));
        }
    }

    /**
     * Checks to see if a word is in the dictionary.
     * 
     * @param word
     *      the string that is checked for
     * @return
     *      true if word is in dictionary
     */
    public boolean check(String word) {
        return check(word.toLowerCase(), root);
    }

    private boolean check(String word, Node tree) {
        if (word.length() == 0 || tree == null) {
            return false;
        }
        else if (word.length() == 1) {
            if (tree.getChild(word.charAt(0)) != null) {
                if (tree.getChild(word.charAt(0)).isEnd()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        else {
            return check(word.substring(1), tree.getChild(word.charAt(0)));
        }
    }

    /**
     * Checks to see if a prefix matches a word in the dictionary.
     * 
     * @param prefix
     *      the prefix that is checked for
     * @return
     *      true if {@param} is a prefix to a word in
     *      the dictionary.
     */
    public boolean checkPrefix(String prefix) {
        return checkPrefix(prefix.toLowerCase(), root);
    }

    private boolean checkPrefix(String prefix, Node tree) {
        if (tree == null) {
            return false;
        }
        if (prefix.length() == 0) {
        	return true;
        }
        else if (prefix.length() == 1) {
            if (tree.getChild(prefix.charAt(0)) != null) {
                return true;
            }
            return false;
        }
        else {
            return checkPrefix(prefix.substring(1), tree.getChild(prefix.charAt(0)));
        }
    }

    /**
     * Print out the contents of the dictionary, in 
     * alphabetical order, one word per line.
     */
    public void print() {
        print("", root);
    }

    private void print(String word, Node node) {
        if (node != null) {

            if (node.isEnd()) {
                System.out.println(word);
            }

            String temp;
            for (char ch : ALPHABET) {
                temp = word;
                temp += ch;
                print(temp, node.getChild(ch));
            }
        }
    }

    /**
     * Removes a word from the dictionary
     * 
     * @param
     *      the word to remove
     */
    public void remove(String word) {
        if (check(word)) {
            remove(word.toLowerCase(), root);
        }
        else {
            System.err.println("remove(): Word is not in dictionary.");
        }
    }

    private void remove(String word, Node tree) {
        if (word.length() == 1) {
            tree.getChild(word.charAt(0)).setFalse();
            prune(tree.getChild(word.charAt(0)));
        }
        else {
            remove(word.substring(1), tree.getChild(word.charAt(0)));
            prune(tree.getChild(word.charAt(0)));
        }
    }

    private void prune(Node node) {
        for (char ch : ALPHABET) {
            if (node.getChild(ch) != null) {
                if (node.getChild(ch).isLeaf() && !node.getChild(ch).isEnd()) {
                    node.makeChildNull(ch);
                }
            }
        }
    }

    /**
     * Returns the entry in the dictionary that 
     * is as close as possible to the parameter word.
     * 
     * @param
     *      the string used to find the closest possible
     *      string in the dictionary
     */
    public String suggest(String word) {
        if (check(word)) {
            return word;
        }
        return suggest(word.toLowerCase(), "", root);
    }

    private String suggest(String word, String suggested, Node tree) {
        suggested += word.charAt(0);

        if (tree.getChild(word.charAt(0)) == null) {
            return findTrue(suggested, tree);
        }
        else if (word.length() == 1) {
            if (tree.getChild(word.charAt(0)) != null) {
                return findTrue(suggested, tree.getChild(word.charAt(0)));
            }
        }

        return suggest(word.substring(1), suggested, tree.getChild(word.charAt(0)));
    }
    
    private String findTrue(String suggested, Node tree) {
        String temp = suggested;

        for (char ch : ALPHABET) {
            if (tree.getChild(ch) != null) {
                temp += ch;
                if (tree.getChild(ch).isEnd()) {
                    return temp;
                }
                else {
                    return findTrue(temp, tree.getChild(ch));
                }
            }
        }

        return "";
    }

}
