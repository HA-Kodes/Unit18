package com.coderscampus.repository;

import com.coderscampus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    // select * from users where username = :username
    List<User> findByUsername(String username);

    // select * from users where name = :name
    List<User> findByName(String name);

    // select * from users where name = :name and username = :username
    List<User> findByNameAndUsername(String name, String username);

    // select * from users where name = :name
    List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2);

    @Query("select u from User u where username = :username")
    List<User> findExactlyOneUserByUsername(String username);

}
