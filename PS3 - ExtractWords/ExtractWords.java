
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ExtractWords{
    public static ArrayList<String> wordList;
	public static void main(String[] args) {
		wordList = new ArrayList<String>();
        try{
            File file = new File("words.txt");
            Scanner scanner = new Scanner(System.in);
            Scanner allWords = new Scanner(file);
            while (allWords.hasNext()){
                wordList.add(allWords.next());
            }
            allWords.close();
            String sentence = scanner.nextLine();
            System.out.println(wordSeparator(sentence, ""));


        } catch (FileNotFoundException e){
            System.out.println(e + " File was not found");
        }

		      
    }
    //fourscoreyearsago
    public static String wordSeparator(String sentence, String answer) {
        if (sentence.equals("")) { // compare using equals() instead of ==
            return answer.trim(); // remove leading/trailing spaces from answer
        }
        for (int i = 1; i <= sentence.length(); i++) { // use <= instead of +
            String currentString = sentence.substring(0, i);
            if (wordList.contains(currentString)) {
                String newAnswer = answer + " " + currentString;
                String remainingSentence = sentence.substring(i);
                String result = wordSeparator(remainingSentence, newAnswer);
                if (result != null) { // only return if a valid result was found
                    return result;
                }
            }
        }
        return null; // return null if no valid result was found
    }
    
   

}
