package Normalize;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/Normalize/" + filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line.trim()).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }

    public void writeFile(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/Normalize/" + filename));
        writer.write(content);
        writer.close();
    }
}
