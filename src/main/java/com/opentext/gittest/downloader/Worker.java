package com.opentext.gittest.downloader;

import com.opentext.gittest.client.GitClient;
import com.opentext.gittest.client.HttpClient;
import com.opentext.gittest.model.Block;
import com.opentext.gittest.service.DefaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.Callable;

import static com.opentext.gittest.model.State.*;

public class Worker implements Callable<Boolean> {

    private static final Logger log = LoggerFactory.getLogger(DefaultService.class);

    private DefaultService service;
    private Block block;

    public Worker(DefaultService service, Block block) {
        this.service = service;
        this.block = block;
    }

    @Override
    public Boolean call() {
        try {
            log.info("{} Downloading...", block.getIndex());
            service.updateBlockState(block.getId(), DOWNLOADING);

            new File(block.getFolder()).mkdir();
            new HttpClient().download(block);

            log.info("{} Uploading...", block.getIndex());
            service.updateBlockState(block.getId(), UPLOADING);

            new GitClient().push(block);

            log.info("{} Success", block.getIndex());
            service.updateBlockState(block.getId(), SUCCESS);
            return true;
        } catch (Throwable e) {
            log.error("{} Error", block.getIndex(), e);
            service.updateBlockState(block.getId(), ERROR);
            return false;
        }
    }

}
