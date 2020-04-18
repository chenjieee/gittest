package com.opentext.gittest.model;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Model {

    public static final long TIME = System.currentTimeMillis();

    private String url;
    private long length = 0;
    private long count = 0;
    private int round = 0;
    private long startBlock = 0;
    private long endBlock = 0;
    private List<Block> blocks = new ArrayList<>();

    private long speedTotal = 0;
    private long speedTime = 1;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public long getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(long startBlock) {
        this.startBlock = startBlock;
    }

    public long getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(long endBlock) {
        this.endBlock = endBlock;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public long getSpeedTotal() {
        return speedTotal;
    }

    public void setSpeedTotal(long speedTotal) {
        this.speedTotal = speedTotal;
    }

    public long getSpeedTime() {
        return speedTime;
    }

    public void setSpeedTime(long speedTime) {
        this.speedTime = speedTime;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

}
