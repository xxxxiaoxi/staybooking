package com.laioffer.staybooking.exception;
//无法删除因为还有人在预定此stay
public class StayDeleteException extends RuntimeException {
    public StayDeleteException(String message) {
        super(message);
    }
}