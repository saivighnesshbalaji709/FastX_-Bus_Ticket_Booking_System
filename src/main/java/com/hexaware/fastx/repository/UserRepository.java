package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<User> getAllUsers();
    Optional<User> findByEmail(String email); 
    boolean existsByEmail(String email);
}
