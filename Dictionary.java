import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dictionary {

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
        root = add(word, root);
        // Make sure to set the last node to true.
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

    }

    /**
     * Print out the contents of the dictionary, in 
     * alphabetical order, one word per line.
     */
    public void print() {

    }

    /**
     * Removes a word from the dictionary
     * 
     * @param
     *      the word to remove
     */
    public void remove(String word) {

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

    }

    private Node add(String word, Node root) {
        if (word ==  null) {

        }

        // Should mean we've reached end of subtree.'
        if (root == null) {
            Node current = root;
            // Can't do current.next, cuz current is null.
            for (char ch : word) {

            }
        }

        else {
            char first = word.charAt(0);
            add(word.substring(1), root.child(first));
        }
    }

    private Node print(Node root) {
        if (root == null) {

        }
        else {
            System.out.println(root.)
        }
    }

}
