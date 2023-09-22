package Normalize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContentProcessor {

    public String normalizeContent(String content) {
        // Split by newline to handle each paragraph separately
        String[] paragraphs = content.split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].trim().length() != 0) {

                paragraphs[i] = normalizeParagraph(paragraphs[i]);
            }
        }

        String result = "";
        for (int i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].trim().length() != 0 && i == paragraphs.length - 1) {
                result += paragraphs[i];
            } else if (paragraphs[i].trim().length() != 0) {
                result += paragraphs[i] + "\n\n";
            }
        }

        return result;
    }

    private String normalizeParagraph(String content) {
        content = content.replaceAll("\\s+", " ").trim();
        content = content
                .replaceAll(" ,", ",")
                .replaceAll(" \\.", ".")
                .replaceAll(" :", ":");

        content = content
                .replaceAll(",", ", ")
                .replaceAll("\\.", ". ")
                .replaceAll(":", ": ");

        content = content.replaceAll("\\s+", " ").trim();
        content = removeSpacesInQuotes(content);
        content = capitalizeAfterPunctuation(content);

        content = content.replaceAll("\\s+", " ");

        if (!content.endsWith(".")) {
            content += ".";
        }
        return content;
    }

    private String capitalizeAfterPunctuation(String text) {
        String[] sentences = text.split("(?<=\\.\\s)");
        for (int i = 0; i < sentences.length; i++) {
            if (!sentences[i].isEmpty()) {
                sentences[i] = sentences[i].substring(0, 1).toUpperCase() + sentences[i].substring(1).toLowerCase();
            }
        }
        return String.join(" ", sentences).trim();
    }

    private String removeSpacesInQuotes(String str) {
        // Ensure there's a space before and after every quote
        str = str.replaceAll("(?<=\\w)\"", " \""); // lookbehind
        str = str.replaceAll("\"(?=\\w)", "\" "); // lookahead

        // Trim spaces inside quotes
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "\"" + m.group(1).trim() + "\"");
        }
        m.appendTail(sb);

        return sb.toString();
    }
}
