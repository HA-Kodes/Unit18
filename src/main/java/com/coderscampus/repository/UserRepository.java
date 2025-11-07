package com.coderscampus.repository;

import com.coderscampus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface UserRepository extends JpaRepository <User, Long> {

}
