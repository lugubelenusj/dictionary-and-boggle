import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Dictionary {

    private Node root;

    public Dictionary() {
        root = new Node();
    }

    public Dictionary(String fileName) throws IOException {
        root = new Node();
        try (BufferedReader reader = Files.newBufferedReader(fileName, Charset.forName("UTF-8"));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                add(line.trim());
            }
        }
    }

    // Make sure to set the last node to true.
    public void add(String word) {
        root = add(word, root);
    }

    public boolean check(String word) {

    }

    public boolean checkPrefix(String prefix) {

    }

    public void print() {

    }

    public void remove(String word) {

    }

    public String suggest(String word) {

    }

    private void add(String word, Node root) {

    }

}
