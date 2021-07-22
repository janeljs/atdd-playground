package com.hellowings.exception;

public class BookNotFoundException extends ElementNotFoundException{
    public BookNotFoundException() {
        super("책을 찾을 수 없습니다.");
    }
}
