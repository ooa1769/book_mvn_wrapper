package com.ooa1769.bs.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(value = {"classpath:kakao.properties", "classpath:naver.properties"})
@SpringBootTest
@AutoConfigureMockMvc
public class ApiBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithUserDetails(value = "test1@test.com", userDetailsServiceBeanName = "securityUserDetailService")
    public void search() throws Exception {
        mvc.perform(get("/api/books/search")
                .param("page", "1")
                .param("size", "10")
                .param("target", "title")
                .param("category", "33")
                .param("query", "스프링")
                .param("apiType", "kakao"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
