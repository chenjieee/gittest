package com.opentext.gittest.model;

import static com.opentext.gittest.model.Constant.FOLDER;
import static com.opentext.gittest.util.Util.fill;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Block {

    private int id;
    private String index;
    private String url;
    private long start;
    private long end;
    private long size;
    private long actualSize;
    private String folder;
    private String file;
    private State state;

    public Block() {
    }

    public Block(int id, String url, long start, long end, State state) {
        this.id = id;
        this.index = fill(id);
        this.url = url;
        this.start = start;
        this.end = end;
        this.size = end - start + 1;
        this.actualSize = 0;
        this.folder = FOLDER + this.index;
        this.file = FOLDER + this.index + "/" + this.index + ".dat";
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getActualSize() {
        return actualSize;
    }

    public void setActualSize(long actualSize) {
        this.actualSize = actualSize;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
