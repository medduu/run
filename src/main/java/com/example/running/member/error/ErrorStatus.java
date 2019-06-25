package com.example.running.member.error;

import lombok.Getter;

@Getter
public enum ErrorStatus {
    ENTITY_NOT_FOUND(400, "검색결과가 없습니다."),
    NO_SUCH_ELEMENT(400, "검색결과가 없습니다.");

    private int status;
    private String message;

    ErrorStatus(int status, String message){
        this.status = status;
        this.message = message;
    }
}
