import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MostAnagrams {
    public static void main(String[] args) {
        List<StringPair> wordList;
        wordList = new ArrayList<StringPair>();
        File file = new File("words.txt");
        int maxWordLength = 0;
        List<String> allSortedWords = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int numOfLines = scan.nextInt();
        try {
            Scanner scanner = new Scanner(file);
            
            for (int i = 0; i < numOfLines; i++){
                String line = scanner.next();
                wordList.add(new StringPair(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(wordList);
        
        if (numOfLines >= wordList.size()){
            System.out.println(maximumAnagram(wordList, wordList.size()));
        }
        else{
            System.out.println(maximumAnagram(wordList,numOfLines));
        }
    
    }
    
    public static int maximumAnagram(List<StringPair> wordList, int numOfLines){
        int maxAnagramCount = 0;
        int currentAnagramsCount = 1;
        for (int i = 1; i < numOfLines; i++){
            if (wordList.get(i).getSorted().equals(wordList.get(i-1).getSorted())){
                currentAnagramsCount++;
            }
            else{
                if (currentAnagramsCount > maxAnagramCount){
                    maxAnagramCount = currentAnagramsCount;
                }
                currentAnagramsCount = 1;
            }
        }
        if (currentAnagramsCount > maxAnagramCount){
            maxAnagramCount = currentAnagramsCount;
        }
        return maxAnagramCount;

    }
    
}
