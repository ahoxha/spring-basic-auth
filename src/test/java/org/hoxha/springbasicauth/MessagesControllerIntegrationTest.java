package org.hoxha.springbasicauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.hoxha.springbasicauth.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MessagesControllerIntegrationTest {

    private final TestRestTemplate template = new TestRestTemplate().withBasicAuth("admin", "secret");

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
        ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);

        assertEquals("Hello from the other side!", responseEntity.getBody());
    }

    @Test
    void when_wrong_user_name_or_password_then_expect_error() {
        TestRestTemplate templateWithoutCredentials = new TestRestTemplate();

        ResponseEntity<String> responseEntity = templateWithoutCredentials.getForEntity(url, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    void testPostInfo() {
        String text = "This is a test message";
        ResponseEntity<Message> responseEntity = template.postForEntity(url, new Message(text), Message.class);

        assertNotNull(responseEntity.getBody());
        assertEquals(text, responseEntity.getBody().getText());
    }
}
