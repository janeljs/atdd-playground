package com.hellowings.web;

import com.hellowings.auth.annotation.LoginRequired;
import com.hellowings.auth.annotation.UserId;
import com.hellowings.service.BookService;
import com.hellowings.web.dto.request.ReviewRequest;
import com.hellowings.web.dto.response.BookResponse;
import com.hellowings.web.dto.response.PartResponse;
import com.hellowings.web.dto.response.ReviewResponse;
import com.hellowings.web.dto.response.MainCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/parts")
    public ResponseEntity<List<PartResponse>> list() {
        return ResponseEntity.ok(bookService.getParts());
    }

    @GetMapping("/parts/{partId}")
    public ResponseEntity<List<MainCategoryResponse>> viewMainCategories(@PathVariable Long partId) {
        return ResponseEntity.ok(bookService.getMainCategories(partId));
    }

    @GetMapping("/subCategories/{subCategoryId}")
    public ResponseEntity<List<BookResponse>> viewBooks(@PathVariable Long subCategoryId) {
        return ResponseEntity.ok(bookService.getAllBooksBySubCategory(subCategoryId));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookResponse> viewBookDetailPage(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @LoginRequired
    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long bookId,
                                                       @RequestBody ReviewRequest reviewRequest,
                                                       @UserId Long userId) {
        return ResponseEntity.ok(bookService.createReview(userId, bookId, reviewRequest));
    }

    @LoginRequired
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(bookService.updateReview(reviewId, reviewRequest));
    }
}
