package org.hoxha.springbasicauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.hoxha.springbasicauth.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MessagesControllerIntegrationTest {

    private RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int serverPort;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + serverPort + contextPath + "/messages";
    }

    @Test
    void testGetInfo() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertEquals("Hello from the other side!", responseEntity.getBody());
    }

    @Test
    void testPostInfo() {
        String text = "This is a test message";
        ResponseEntity<Message> responseEntity = restTemplate.postForEntity(url, new Message(text), Message.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(text, responseEntity.getBody().getText());
    }
}
