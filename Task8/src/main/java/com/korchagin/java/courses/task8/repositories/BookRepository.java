package com.korchagin.java.courses.task8.repositories;

import com.korchagin.java.courses.task8.entityes.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findAllByTitle(String title);
    Book findOneByTitleAndPublishYear(String title, int year);
    List<Book> findAllByTitleStartingWith(String titleStartingWith);
    List<Book> findAllByPublishYear(int year);

    @Query("delete from Book b where b.publishYear < 2000")
    void deleteBookByOlder2000();

    void deleteBookByPublishYearLessThan(int year);

    @Query("select b.publishYear from Book b group by b.publishYear order by b.publishYear ASC")
    List<Integer> findAllPublishYear();
}
