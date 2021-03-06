package com.hellowings.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Book book;

    public Wish(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
