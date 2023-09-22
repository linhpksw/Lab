package Normalize;

import java.io.IOException;

public class TextNormalizer {

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        ContentProcessor processor = new ContentProcessor();

        try {
            String content = fileHandler.readFile("input.txt");
            System.out.println("Input:");
            System.out.println(content);
            String normalizedContent = processor.normalizeContent(content);

            System.out.println("Output:");
            System.out.println(normalizedContent);
            fileHandler.writeFile("output.txt", normalizedContent);
            System.out.println("Text normalized and written to output.txt");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
