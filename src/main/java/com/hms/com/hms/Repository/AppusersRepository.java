package com.hms.com.hms.Repository;

import com.hms.com.hms.Entity.Appusers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppusersRepository extends JpaRepository<Appusers, Long> {
    Optional<Appusers> findByUsername(String username);
    Optional<Appusers> findByEmail(String email);
    Optional<Appusers> findByMobile(String mobile);
}