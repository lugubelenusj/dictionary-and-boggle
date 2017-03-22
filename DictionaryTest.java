import java.io.File;
import java.util.Scanner;

public class DictionaryTest
{
    public static void main(String args[])
    {

        boolean success = true;
        success =  smallTest() && success;
        success =  testWordFile("words.txt") && success;

        printSuccess(success, "All Tests");

    }


    private static boolean smallTest()
    {
        Dictionary d = new Dictionary();

        boolean passed = true;
        try
        {

            String words[] = {"cat", "catapult", "carthorse","cartoon", "dog", "apple", "ape", "breakfast", "breakneck", "queasy", "quash", 
                    "quail", "quick", "reason", "rickshaw", "reality"};

            String nonWords[] = {"carts", "ap", "break", "qu", "reasons", "buttercup", "under"};

            for (int i = 0; i < words.length; i++)
            {
                d.add(words[i]);            
            }
            for (int i = 0; i < nonWords.length; i++)
            {
                if (d.check(nonWords[i]))
                {
                    System.out.println("Found word:"+nonWords[i] + ", shoudn't have");
                    passed = false;
                }
            }
            d.print();


            String wordPairs[][]= {{"catapult", "cat"}, {"cartoon", "cart"}};

            for (String pair[] : wordPairs)
            {
                String wordToRemove = pair[0];
                String commonPrefix = pair[1];
                d.remove(wordToRemove);
                if (d.check(wordToRemove))
                {
                    System.out.println(wordToRemove + " deleted, but still found!"); 
                    passed = false;
                }
                int overlap = commonPrefix.length();
                for (int i = 1; i < wordToRemove.length(); i++)
                {
                    String prefix = wordToRemove.substring(0, i);
                    if (i <= overlap)
                    {
                        if (!d.checkPrefix(prefix))
                        {
                            System.out.println("After delete of " + wordToRemove + ", checkPrefix("+prefix+") fails, and should succeed"); 
                            passed = false;
                        }
                    }
                    else
                    {
                        if (d.checkPrefix(prefix))
                        {
                            System.out.println("After delete of " + wordToRemove + ", checkPrefix("+prefix+") succeeds, and should fail"); 
                            passed = false;
                        }
                    }

                }
            }    
            d.print();

        }

        catch (Exception e)
        {

            passed = false;
            System.out.println("Exception in smallTest: " + e.getMessage());
        }

        printSuccess(passed, "smallTest");
        return passed;

    }

    private static boolean testWordFile(String filename)
    {
        boolean passed = true;        
        try
        {
            Dictionary d = new Dictionary(filename);
            boolean paseedCheck = true;
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext())
            {
                String nextStr = s.nextLine();
                if (nextStr.length() > 0)
                {
                    if (!d.check(nextStr))
                    {
                        paseedCheck = false;
                        System.out.println("Failed to find word: " + nextStr);                        
                    }
                    for (int i = 0; i <= nextStr.length(); i++)
                    {
                        if (!d.checkPrefix(nextStr.substring(0,i)))
                        {
                            paseedCheck = false;
                            System.out.println("Failed to find prefix: " + nextStr.substring(0,i) + " of " + nextStr);       
                        }   
                    }
                }
            }
            printSuccess(paseedCheck, "Check and Check Prefix");
            passed = passed && paseedCheck;
            String badWords[] = {"accer", "fatte", "flox", "forg", "forsoom"};
            for (int i = 0; i < badWords.length; i++)
            {
                String result = d.suggest(badWords[i]);
                System.out.println("Trying " + badWords[i] + " Suggestion = " + result);
                if (!d.check(result))
                {
                    System.out.println("    Suggestion " +result + " not in dictionary.");   
                    passed = false;
                }
            }
            String goodWords[] = { "cat", "baseball", "original"};
            for (int i = 0; i < goodWords.length; i++)
            {
                String result = d.suggest(goodWords[i]);
                if (!result.toLowerCase().equals(goodWords[i].toLowerCase()))
                {
                    System.out.println("Word " + goodWords[i] + " is in the dictionary -- suggest should return it");
                    System.out.println("   returned " + result + " instead");
                    passed = false;
                }
            }

        }       
        catch (Exception e)
        {
            System.out.println(e);
            passed = false;
        }
        return passed;
    }

    private static void printSuccess(boolean success, String description)
    {
        if (success)
        {
            System.out.println(description + " SUCCESS");
        }
        else
        {
            System.out.println(description + " FAILURE");

        }        
    }




}
