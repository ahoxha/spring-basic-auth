package org.hoxha.springbasicauth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hoxha.springbasicauth.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@AutoConfigureMockMvc
class MessagesControllerMockMvcTest {

    private static final String URL = "/messages";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", password = "secret")
    void testGetMessages() throws Exception {
        mockMvc.perform(get(URL)) //
                .andExpect(status().isOk()) //
                .andExpect(content().string("Hello from the other side!"));
    }

    @Test
    @WithMockUser(username = "admin", password = "secret")
    void testPostMessages() throws Exception {
        Message message = new Message("This is a test message");
        mockMvc.perform(createPostRequest(message)) //
                .andExpect(status().isOk()) //
                .andExpect(content().json(toJson(message)));
    }

    @Test
    void when_incorrect_credentials_then_expect_error() throws Exception {
        mockMvc.perform(get(URL)) //
                .andExpect(status().isUnauthorized());
    }

    private MockHttpServletRequestBuilder createPostRequest(Message message) throws JsonProcessingException {
        return post(URL).contentType(MediaType.APPLICATION_JSON).content(toJson(message));
    }

    private String toJson(Message message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }
}
