package com.github.zenuralimov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Base64;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static String login(String url, String username, String password) throws JsonProcessingException {
        String login = username + ":" + password;
        String base64login = Base64.getEncoder().encodeToString(login.getBytes());

        Connection.Response response = null;
        try {
            response = Jsoup
                    .connect(url)
                    .header("Authorization", "Basic " + base64login)
                    .ignoreContentType(true) // https://stackoverflow.com/questions/16327105/connection-error-org-jsoup-unsupportedmimetypeexception-unhandled-content-typ
                    .execute();

            logger.info("Response Status Code: {}", response.statusCode());
            logger.info("Response Content Type: {}", response.contentType());
            logger.info("Response Body: {}", response.body());

        } catch (IOException ex) {
            logger.error("{}", ex.getMessage());
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.body());

        return jsonNode.toString();
    }
}
