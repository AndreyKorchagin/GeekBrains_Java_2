package com.korchagin.java.courses.task8.utils;

import com.korchagin.java.courses.task8.entityes.Book;
import com.korchagin.java.courses.task8.entityes.Genre;
import com.korchagin.java.courses.task8.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private String filterParam;

    public BookFilter(Map<String, String> params){
        spec = Specification.where(null);
        if (params.containsKey("maxPrice")) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
        }
        if (params.containsKey("minPrice")) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
        }
        if (params.containsKey("titlePart")) {
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
        }
        if (params.containsKey("year")){
            spec = spec.and(BookSpecifications.publishYearEgual(Integer.parseInt(params.get("year"))));
        }
        if (params.containsKey("genre")){
            List<String> listParams = Arrays.asList(params.get("genre").split("-"));
            Specification<Book> specOr = Specification.where(null);
            for (String s: listParams) {
                specOr = specOr.or(BookSpecifications.genreEqual(Genre.valueOf(s)));
            }
            spec = spec.and(specOr);
        }
    }

}

