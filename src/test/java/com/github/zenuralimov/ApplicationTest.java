package com.github.zenuralimov;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static com.github.zenuralimov.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApplicationTest {

    @Test
    void login() throws JsonProcessingException {
        String expected = "{\"authenticated\":true,\"user\":\"foo\"}";

        assertEquals(expected, Application.login(URL, USERNAME, PASSWORD));
    }

    @Test
    void errorAuth() throws JsonProcessingException {
        assertNull(Application.login(URL, USERNAME, WRONG_PASSWORD));
    }
}