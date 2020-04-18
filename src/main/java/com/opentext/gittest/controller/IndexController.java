package com.opentext.gittest.controller;

import com.opentext.gittest.downloader.Downloader;
import com.opentext.gittest.model.Model;
import com.opentext.gittest.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private Downloader downloader;

    @Autowired
    private DefaultService service;

    @PostMapping("api/start")
    public void start(@RequestBody Model model) {
        service.initBlocks(model);
        downloader.start();
    }

    @GetMapping("api/load")
    public Model load() {
        service.updateBlockActualSize();
        return service.getModel();
    }

}
