package com.hellowings.web;

import com.hellowings.auth.annotation.LoginRequired;
import com.hellowings.auth.annotation.UserId;
import com.hellowings.service.UserService;
import com.hellowings.web.dto.response.AuthUserResponse;
import com.hellowings.web.dto.response.UserWishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserWishResponse> userInfo(@PathVariable("userId") Long userId) throws Exception {
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponse> login(@RequestParam String code) {
        return ResponseEntity.ok(userService.login(code));
    }

    @LoginRequired
    @PostMapping("/books/{bookId}/wishes")
    public ResponseEntity<UserWishResponse> addWish(@PathVariable Long bookId, @UserId Long userId) {
        return ResponseEntity.ok(userService.addWish(userId, bookId));
    }

    @LoginRequired
    @DeleteMapping("/books/{bookId}/wishes")
    public void deleteWish(@PathVariable Long bookId) {
        userService.deleteWish(bookId);
    }

}
