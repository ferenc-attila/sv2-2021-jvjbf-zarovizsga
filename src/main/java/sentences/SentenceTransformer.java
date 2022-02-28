package sentences;

import java.util.List;

public class SentenceTransformer {

    private static final List<Character> END_CHARACTERS = List.of('.', '!', '?');

    public String shortenSentence(String sentence) {
        validateSentence(sentence);
        String[] words = sentence.split(" ");
        if (words.length < 5) {
            return sentence;
        }
        return words[0] + " ... " + words[words.length - 1];
    }

    private void validateSentence(String sentence) {
        checkSentenceIsNotNull(sentence);
        checkSentenceStartsWithUpperCase(sentence);
        checkSentenceEndsCorrectly(sentence);
    }

    private void checkSentenceIsNotNull(String sentence) {
        if (sentence == null || sentence.isBlank()) {
            throw new IllegalArgumentException("Sentence cannot be null or empty!");
        }
    }

    private void checkSentenceStartsWithUpperCase(String sentence) {
        if (!Character.isUpperCase(sentence.charAt(0))) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
    }

    private void checkSentenceEndsCorrectly(String sentence) {
        if (!END_CHARACTERS.contains(sentence.charAt(sentence.length() - 1))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
