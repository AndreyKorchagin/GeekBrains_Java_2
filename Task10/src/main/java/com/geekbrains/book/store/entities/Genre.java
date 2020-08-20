package com.geekbrains.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Genre {
    FANTASY("Фантастика"),
    DETECTIVE("Детектив"),
    ROMANCE("Роман"),
    THRILLER("Триллер");

    private final String genreName;
}
