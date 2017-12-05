package com.evise.filesearch.service;

import java.util.List;

/**
 * @author Bala Maramreddy
 */
public interface FileSearchService {
    /**
     * Search all the files recursively for the given words and return list of file names that have all the words.
     *
     * @param searchPath the path that must be searched recursively
     * @param words the words for searching
     * @return
     */
    List<String> searchFiles(final String searchPath, String[] words);
}
