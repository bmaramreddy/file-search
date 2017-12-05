package com.evise.filesearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class FileSearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchFilesForWords() {
        return "Hello searching...";
    }
}
