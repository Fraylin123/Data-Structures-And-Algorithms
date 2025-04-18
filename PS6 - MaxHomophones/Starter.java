
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Starter {

    public static void main(String[] args) {
        //OALDictionary<String, Pronunciation> PDict = new OALDictionary<String, Pronunciation>();
        UALDictionary<String, Pronunciation> PDict = new UALDictionary<String, Pronunciation>();
        File file = new File("../resource/asnlib/publicdata/cmudict.0.7a.txt");

        long start = System.currentTimeMillis();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {                String line = scanner.nextLine();
                if (line.substring(0, 3).equals(";;;"))
                    continue; // skip comment lines
                Pronunciation p = new Pronunciation(line);
                PDict.insert(p.getWord(), p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long middle = System.currentTimeMillis();

        System.out.println("Loaded dictionary; elapsed time "+(middle-start));

        int count = 0;
        for (Pronunciation p : PDict.values()) {
            ++count;
            System.out.println(p);
            if(count>5)
                break;
        }
        long end = System.currentTimeMillis();
        System.out.println("Run times: load dictionary= " + (middle - start)
                           + " process= " + (end - middle) + " total= " + (end - start));
    }
}
