package com.jpmorgan.hometaskdemo.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    AccountService service;
    Account account;

    @Test
    void getAccountValidation() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("source","source1");
        item.put("isValid", true);
        item.put("source","source2");
        item.put("isValid", true);
        array.put(item);
        json.put("results", array);


        JSONObject otherItems = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.put("someUrl");
        arr.put("anotherUrl");
        otherItems.put("accountId",12144);
        otherItems.put("source",arr);

        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(service.validateAccountWithSource(account)).thenReturn(json.toString());
        String url = "/api/v1/account/postbody";
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(otherItems.toString()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

//        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/account/postbody");
//        MvcResult result = mvc.perform(request).andReturn();
//        assertEquals("Something", result.getResponse().getContentAsString());
    }
}