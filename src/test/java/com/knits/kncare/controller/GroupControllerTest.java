package com.knits.kncare.controller;

import com.knits.kncare.dto.GroupDto;
import com.knits.kncare.service.GroupService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {

    @MockBean
    private GroupService groupService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should return a Group when making get request to endpoint - api/groups/{id}")
    void shouldGetGroupById() throws Exception {
        GroupDto groupDto = new GroupDto(100L, "Group Estonia", "Group Description");

        Mockito.when(groupService.getbyId(100L)).thenReturn(groupDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/groups/100"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Group Estonia")));
    }
}