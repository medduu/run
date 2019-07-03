package com.example.running.member.controller;

import com.example.running.RunningApplication;
import com.example.running.member.dto.MemberRequestDto;
import com.example.running.member.dto.MemberResponseDto;
import com.example.running.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunningApplication.class)
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MemberService memberService;

    @Test
    @WithMockUser(roles = "MEMBER")
    public void 회원가입_성공() throws Exception{
        //given
        MemberRequestDto dto = new MemberRequestDto ("testRestApi", "1111", "test");

        //when
        ResultActions resultActions = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString (dto)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(dto.getUsername ()))
                .andExpect(jsonPath("name").value(dto.getName()));
    }

    @Test
    @WithMockUser(roles = "MEMBER")
    public void 회원수정_성공() throws Exception{
        //given
        MemberRequestDto insertDto = new MemberRequestDto ("testRestApi", "1111", "test");
        MemberResponseDto responseDto = memberService.signUp (insertDto);

        MemberRequestDto updateDto = new MemberRequestDto (responseDto.getId (), "testRestApii", "1111a", "test111");

        //when
        ResultActions resultActions = mvc.perform(put("/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString (updateDto)))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(updateDto.getName()));
    }

    @Test
    @WithMockUser(roles = "MEMBER")
    public void 회원조회_성공() throws Exception{
        //given
        MemberRequestDto insertDto = new MemberRequestDto ("testRestApi", "1111", "test");
        MemberResponseDto responseDto = memberService.signUp (insertDto);

        //when
        ResultActions resultActions = mvc.perform(get("/members/{id}", responseDto.getId ())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(insertDto.getUsername ()))
                .andExpect(jsonPath("name").value(insertDto.getName()));
    }

    @Test
    @WithMockUser(roles = "MEMBER")
    public void 회원삭제_성공() throws Exception{
        //given
        MemberRequestDto insertDto = new MemberRequestDto ("testRestApi", "1111", "test");
        MemberResponseDto responseDto = memberService.signUp (insertDto);

        //when
        mvc.perform(delete("/members/{id}", responseDto.getId ())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        ResultActions resultActions = mvc.perform(get("/members/{id}", responseDto.getId ())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("검색결과가 없습니다."))
                .andExpect(jsonPath("status").value(400));
    }

}
