package com.korchagin.java.courses.task7.repositories;

import com.korchagin.java.courses.task7.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
