package com.example.running.member.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(ErrorStatus status){
        this.status = status.getStatus ();
        this.message = status.getMessage ();
    }
}
