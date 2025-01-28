package com.example.sampleapp.repository;

import com.example.sampleapp.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTypeRepository extends JpaRepository<UsersType, Long> {
}
