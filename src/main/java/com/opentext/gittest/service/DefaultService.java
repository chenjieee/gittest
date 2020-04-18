package com.opentext.gittest.service;

import com.opentext.gittest.client.HttpClient;
import com.opentext.gittest.model.Block;
import com.opentext.gittest.model.Model;
import com.opentext.gittest.model.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.opentext.gittest.model.Constant.BLOCK;
import static com.opentext.gittest.model.State.READY;
import static com.opentext.gittest.model.State.SKIPPED;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class DefaultService {

    private static final Logger log = LoggerFactory.getLogger(DefaultService.class);

    private Model model = new Model();

    public synchronized Model getModel() {
        return model;
    }

    public synchronized void initBlocks(Model m) {
        if (isNotBlank(m.getUrl())) {
            model.setUrl(m.getUrl());
            long length = getLength();
            if (length > 0) {
                model.setLength(length);
                model.setCount((model.getLength() - 1) / BLOCK + 1);
                model.setStartBlock(m.getStartBlock());
                model.setEndBlock(m.getEndBlock() == 0 ? model.getCount() - 1 : m.getEndBlock());
                log.info("length: {}", model.getLength());
                log.info("count: {}", model.getCount());
                for (int i = 0; i < model.getCount(); i++) {
                    long start = BLOCK * i;
                    long end = BLOCK * (i + 1) - 1;
                    if (end >= length) {
                        end = length - 1;
                    }
                    State state = (i >= model.getStartBlock() && i <= model.getEndBlock()) ? READY : SKIPPED;
                    Block block = new Block(i, m.getUrl(), start, end, state);
                    model.getBlocks().add(block);
                }
            }
        }
    }

    private synchronized long getLength() {
        try {
            return new HttpClient().getLength(model);
        } catch (Exception e) {
            log.error("Failed to get length.", e);
            return 0;
        }
    }

    public synchronized void updateBlockActualSize() {
        for (Block block : model.getBlocks()) {
            File file = new File(block.getFile());
            if (file.exists()) {
                block.setActualSize(file.length());
            }
        }
    }

    public synchronized void updateBlockState(int id, State state) {
        model.getBlocks().get(id).setState(state);
    }

}
