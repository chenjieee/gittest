package com.opentext.gittest.client;

import com.opentext.gittest.model.Block;
import com.opentext.gittest.model.Model;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

import static com.opentext.gittest.model.Constant.TIMEOUT;

public class HttpClient {

    private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

    public long getLength(Model model) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(model.getUrl());
            try (CloseableHttpResponse response = client.execute(get)) {
                HttpEntity entity = response.getEntity();
                return entity.getContentLength();
            }
        }
    }

    public void download(Block block) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(block.getUrl());
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(TIMEOUT)
                    .setConnectTimeout(TIMEOUT)
                    .setSocketTimeout(TIMEOUT)
                    .build();
            get.setConfig(requestConfig);
            get.addHeader("Range", "bytes=" + block.getStart() + "-" + block.getEnd());
            try (CloseableHttpResponse response = client.execute(get)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200 && statusCode != 206) {
                    throw new IllegalArgumentException("Invalid HTTP status code: " + statusCode);
                }
                HttpEntity entity = response.getEntity();
                try (FileOutputStream fos = new FileOutputStream(block.getFile())) {
                    entity.writeTo(fos);
                }
            }
        }
    }

}
