package com.evise.filesearch.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface CacheService {

//    /**
//     * Check if the coma separated words string exists in cache.
//     *
//     * @param words the coma separated words string
//     * @return
//     */
//    boolean wordExists(final String words);

    /**
     * Add the coma separated words string to cache.
     *
     * @param words the coma separated words string
     */
    void addWordsToCache(final String words);

    /**
     * Search the cache for the coma separated words and return list of words that do not exist in cache.
     *
     * @param filesSearchPath the path to search
     * @param words words the coma separated words string
     * @return
     */
    public List<String> getNonCachedWordsList(String filesSearchPath, String words);

    /**
     * Clear cache when search path or files content have changed.
     */
    public void clearCache();

    /**
     * Check if the cache is valid. It is not valid if the search path is different from last search or file contents has changed.
     *
     * @param filesSearchPath
     * @return
     */
    public boolean isValidCache(String filesSearchPath);
}
