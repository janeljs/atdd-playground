package com.hellowings.exception;

public class CategoryNotFoundException extends ElementNotFoundException {
    public CategoryNotFoundException() {
        super("카테고리를 찾을 수 없습니다.");
    }
}
