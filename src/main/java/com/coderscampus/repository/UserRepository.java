package com.coderscampus.repository;

import com.coderscampus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}
