package com.laioffer.staybooking.exception;
//时间格式有问题
public class InvalidReservationDateException extends RuntimeException {
    public InvalidReservationDateException(String message) {
        super(message);
    }
}