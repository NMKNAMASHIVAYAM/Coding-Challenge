package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
	Optional<UserData> findByName(String username);
}
