package com.evise.filesearch.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CacheServiceImpl implements CacheService {

    /**
     * Retain the search path for validating cache data
     */
    private String filesSearchPath;

    /**
     * The set to hold the cached data.
     */
    private final Set<String> cacheSet = new HashSet<>();

    /**
     * Store the details of last modified date/time.
     */
    private Map<String, Long> filesLastModifiedMapForLastSearch = new HashMap<>();

//    @Override
//    public boolean wordExists(String word) {
//        if (!cacheSet.isEmpty()) {
//            return cacheSet.contains(word.trim());
//        }
//        return false;
//    }

    @Override
    public List<String> getNonCachedWordsList(String filesSearchPath, String words) {
        //isValidCache();

        List<String> wordsList = Arrays.asList(words.replace(" ", "").split(","));
        List<String> nonCachedWordsList = new ArrayList<>(wordsList.size());
        wordsList.forEach(word -> {
            if (!cacheSet.contains(word)) {
                nonCachedWordsList.add(word);
            }
        });
        return nonCachedWordsList;
    }

    @Override
    public void clearCache() {
        cacheSet.clear();
    }

    @Override
    public boolean isValidCache(String filesSearchPath) {
        boolean isValid = true;

        // first check files root path
        if (this.filesSearchPath == null || !this.filesSearchPath.equals(filesSearchPath)) {
            isValid = false;
        }

        // Read files data from current search path.
        Map<String, Long> filesLastUpdatedMapForNewSearch = getLastModifiedDateTime(filesSearchPath);

        if (!cacheSet.isEmpty() && !filesLastModifiedMapForLastSearch.isEmpty()) {
            // now check files last modifiled stamp
            for (Map.Entry<String, Long> entry : filesLastModifiedMapForLastSearch.entrySet()) {
                String key = entry.getKey();
                Long newDateTimeVal = filesLastUpdatedMapForNewSearch.get(key);

                // If a file is missing or updated since last search, then cache is not valid.
                if (newDateTimeVal == null || !entry.getValue().equals(newDateTimeVal)) {
                    isValid = false;
                }
            }
        }

        filesLastModifiedMapForLastSearch = filesLastUpdatedMapForNewSearch;

        return isValid;
    }

    @Override
    public void addWordsToCache(String words) {
        if (words != null) {
            // single word
            if (words.indexOf(',') == -1) {
                cacheSet.add(words.trim());
            } else {
                // multiple words
                List<String> wordsList = Arrays.asList(words.split(","));
                wordsList.forEach(s -> cacheSet.add(s.trim()));
            }
        }
    }

    private Map<String, Long> getLastModifiedDateTime(final String path) {
        Map<String, Long> filesLastUpdatedMap = new HashMap<>();

        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        File file = p.toFile();
                        filesLastUpdatedMap.put(file.getAbsolutePath(), file.lastModified());
                    });
        } catch (Exception e) {
            // handle exception
        }

        return filesLastUpdatedMap;
    }
}
