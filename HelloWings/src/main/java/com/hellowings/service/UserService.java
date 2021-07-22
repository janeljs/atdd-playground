package com.hellowings.service;

import com.hellowings.auth.OAuth;
import com.hellowings.auth.dto.AccessTokenResponseDTO;
import com.hellowings.auth.dto.OAuthUserResponseDTO;
import com.hellowings.auth.service.JwtService;
import com.hellowings.domain.Book;
import com.hellowings.domain.User;
import com.hellowings.domain.Wish;
import com.hellowings.repository.BookRepository;
import com.hellowings.repository.UserRepository;
import com.hellowings.repository.WishRepository;
import com.hellowings.web.dto.response.AuthUserResponse;
import com.hellowings.web.dto.response.UserWishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OAuth oauth;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BookRepository bookRepository;
    private final WishRepository wishRepository;

    public UserWishResponse findUser(Long id) {
        User user = findUserById(id);
        return UserWishResponse.of(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void deleteWish(Long bookId) {
        wishRepository.deleteWishByBookId(bookId);
    }

    public UserWishResponse addWish(Long userId, Long bookId) {
        User user = findUserById(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));
        Wish wish = new Wish(user, book);
        bookRepository.save(book.addWish(wishRepository.save(wish)));
        User updatedUser = findUserById(userId);
        return UserWishResponse.of(updatedUser);
    }

    public AuthUserResponse login(String code) {
        AccessTokenResponseDTO token = oauth.getToken(code);
        OAuthUserResponseDTO userInfo = oauth.getUserInfo(token.getAccessToken());
        String userName = userInfo.getLogin();

        if (verifyUser(userName)) {
            User user = findUserByName(userName);
            return AuthUserResponse.of(user, jwtService.createToken(user));
        }

        User user = User.of(userInfo);
        userRepository.save(user);
        return AuthUserResponse.of(user, jwtService.createToken(user));
    }

    private boolean verifyUser(String userName) {
        return userRepository.findByName(userName).isPresent();
    }

    private User findUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
}
