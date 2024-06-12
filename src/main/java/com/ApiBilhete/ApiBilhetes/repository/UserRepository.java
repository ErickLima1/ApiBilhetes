package com.ApiBilhete.ApiBilhetes.repository;

import com.ApiBilhete.ApiBilhetes.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
}
