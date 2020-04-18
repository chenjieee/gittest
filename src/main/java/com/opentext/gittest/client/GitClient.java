package com.opentext.gittest.client;

import com.opentext.gittest.model.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.opentext.gittest.model.Constant.GIT_URL;

public class GitClient {

    private static final Logger log = LoggerFactory.getLogger(GitClient.class);

    public void push(Block block) throws Exception {
        File folder = new File(block.getFolder());
        String gitUrl = String.format(GIT_URL, block.getIndex());

        exec("git init", folder);
        exec("git add .", folder);
        exec("git commit -m comment", folder);
        exec("git remote add origin " + gitUrl, folder);
        exec("git push -u origin master", folder);
    }

    private int exec(String command, File folder) throws Exception {
        Process process = Runtime.getRuntime().exec(command, new String[0], folder);
        process.waitFor();
        int exitValue = process.exitValue();
        if (exitValue != 0) {
            throw new RuntimeException("Error executing '" + command + "'. Exit value: " + exitValue);
        }
        return exitValue;
    }

}
