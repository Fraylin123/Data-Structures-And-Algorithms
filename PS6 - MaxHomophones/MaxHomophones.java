import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class MaxHomophones {

    public static void main(String[] args) {
        UALDictionary<String, Pronunciation> PDict = new UALDictionary<String, Pronunciation>();
        File file = new File("cmudict.0.7a.txt");

        try {
            Scanner scanner = new Scanner(file);
            int numWords = 0;
            while (scanner.hasNextLine()) {  //Counting the number of words
                String line = scanner.nextLine();
                if (line.substring(0, 3).equals(";;;"))
                    continue; // skip comment lines
                numWords++;
            }
            scanner.close();
            scanner = new Scanner(file);  //Resetting the scanner
            for (int i = 0; i < numWords; i++){
                String line = scanner.nextLine();
                if (line.substring(0, 3).equals(";;;"))
                    continue; // skip comment lines
                Pronunciation p = new Pronunciation(line);
                if (p.getWord().length() == 3) {  //Only add 3-letter words to the dictionary
                    PDict.insert(p.getWord(), p);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashSet<String> homophoneSet = new HashSet<>(); //Stores all of the 3-letter words with homophones
        for (Pronunciation element : PDict.values()){
            for (Pronunciation innerElement: PDict.values()){
                if (innerElement.getWord().compareTo(element.getWord()) == 0) { //Same word, skip
                    continue;
                }
                if (innerElement.getWord().compareTo(element.getWord()) < 0){ //Avoids checking the same pair twice
                    continue;
                }
                if (element.getPhonemes().length() == innerElement.getPhonemes().length() && element.getPhonemes().equals(innerElement.getPhonemes())){
                    String word = innerElement.getWord();
                    homophoneSet.add(word.strip());
                }
            }
        }

        System.out.println("Number of 3-letter words pronounced the same as some other 3-letter word: " + homophoneSet.size());
    }
}
