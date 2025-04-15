package comprehensive;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MarkovChain {
    private final Map<String, Map<String, Double>> wordMap = new HashMap<>();

    public void train(String[] words) {
        // Temporary map to count occurrences
        Map<String, Map<String, Integer>> countMap = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i].toLowerCase();
            String next = words[i + 1].toLowerCase();

            countMap.putIfAbsent(curr, new HashMap<>());
            Map<String, Integer> nextWords = countMap.get(curr);
            nextWords.put(next, nextWords.getOrDefault(next, 0) + 1);
        }

        // Convert counts to probabilities and store in wordMap
        for (String word : countMap.keySet()) {
            Map<String, Integer> nextCounts = countMap.get(word);
            int totalNext = nextCounts.values().stream().mapToInt(Integer::intValue).sum();

            Map<String, Double> nextProbs = new HashMap<>();
            for (Map.Entry<String, Integer> entry : nextCounts.entrySet()) {
                double probability = (double) entry.getValue() / totalNext;
                nextProbs.put(entry.getKey(), probability);
            }

            wordMap.put(word, nextProbs);
        }
    }

    // Get most probable next word
    public String mostLikelyNext(String word) {
        word = word.toLowerCase();
        Map<String, Double> nextWords = wordMap.get(word);
        if (nextWords == null || nextWords.isEmpty()) return null;

        return nextWords.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Get all next-word probabilities for a word
    public Map<String, Double> getNextWordProbabilities(String word) {
        return wordMap.getOrDefault(word.toLowerCase(), Collections.emptyMap());
    }

}
