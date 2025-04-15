package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scan {
    private Scanner input;

    public Scan(String filename) {
        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
    }

    public String[] wordList() {
        ArrayList<String> words = new ArrayList<>();

        while (input.hasNext()) {
            // Use regex to remove unwanted punctuation and split words
            String word = input.next();
            word = word.replaceAll("[^a-zA-Z']", ""); // Keeps letters and apostrophes
            if (!word.isEmpty()) {
                words.add(word.toLowerCase());
            }
        }

        return words.toArray(new String[0]);
    }
}
