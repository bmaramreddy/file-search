package com.evise.filesearch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheServiceTest {
    private CacheService cacheService;

    @Before
    public void setup() {
        cacheService = new CacheServiceImpl();
    }

    @Test
    public void testSomeWordsExistInCache() {
        cacheService.addWordsToCache("red,blue,green");

        List<String> nonCachedWordsListExpected = Arrays.asList("yellow,white".split(","));
        List<String> actualnonCachedWordsList = cacheService.getNonCachedWordsList("C:/Users/BMaramreddy/temp", "red,white,blue,yellow,green");

        Assert.assertTrue(areListsEqual(nonCachedWordsListExpected, actualnonCachedWordsList));
    }

    @Test
    public void testAllWordsExistInCache() {
        cacheService.addWordsToCache("red,blue,green");

        List<String> nonCachedWordsListExpected = new ArrayList<>();
        List<String> actualnonCachedWordsList = cacheService.getNonCachedWordsList("C:/Users/BMaramreddy/temp","red,blue,green");

        Assert.assertTrue(areListsEqual(nonCachedWordsListExpected, actualnonCachedWordsList));
    }

    @Test
    public void testNoWordsExistInCache() {
        List<String> nonCachedWordsListExpected = Arrays.asList("red,white,blue,yellow,green".split(","));
        List<String> actualnonCachedWordsList = cacheService.getNonCachedWordsList("C:/Users/BMaramreddy/temp","red,white,blue,yellow,green");

        Assert.assertTrue(areListsEqual(nonCachedWordsListExpected, actualnonCachedWordsList));
    }


    // compare two lists of words
    private boolean areListsEqual(List<String> wordsList1, List<String> wordsList2) {
        if (emptyOrNullList(wordsList1) && emptyOrNullList(wordsList2)) {
            return true;
        }

        if (emptyOrNullList(wordsList1) != emptyOrNullList(wordsList2)) {
            return false;
        }

        if (wordsList1.size() != wordsList1.size()) {
            return false;
        }

        for (String word : wordsList1) {
            if (!wordsList2.contains(word)) {
                return false;
            }
        }
        return true;
    }

    private boolean emptyOrNullList(List<String> wordsList) {
        return (wordsList == null || wordsList.isEmpty());
    }
}
