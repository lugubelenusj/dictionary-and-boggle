import java.io.IOException;

public class MyTest {

    public static void main(String[] args) throws IOException{

        Dictionary dict = new Dictionary();

        dict.add("grape");
        dict.add("cat");
        dict.add("fish");
        dict.add("dog");
        dict.add("doug");

        System.out.println("checkPrefix()");
        System.out.println(dict.check("grape"));
        System.out.println(dict.checkPrefix("g"));
        System.out.println(dict.checkPrefix("cat"));
        System.out.println(dict.checkPrefix("fi"));
        System.out.println();

        System.out.println("remove()");
        dict.remove("grape");
        System.out.println(dict.check("grape"));
        System.out.println();

        System.out.println("suggest()");
        System.out.println(dict.suggest("d"));
        System.out.println(dict.suggest("dou"));
        System.out.println(dict.suggest("cat"));
        System.out.println();

        dict.print();
        System.out.println();

        // Dictionary dict2 = new Dictionary("words.txt");
        // dict2.print();

    }

}