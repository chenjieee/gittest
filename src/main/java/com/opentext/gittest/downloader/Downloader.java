package com.opentext.gittest.downloader;

import com.opentext.gittest.model.Block;
import com.opentext.gittest.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.opentext.gittest.model.State.READY;

@Service
public class Downloader {

    @Autowired
    private DefaultService service;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private boolean running = false;

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;

        List<Block> blocks = service.getModel().getBlocks();
        blocks.forEach(block -> {
            if (block.getState() == READY) {
                executor.submit(new Worker(service, block));
            }
        });
    }

}
