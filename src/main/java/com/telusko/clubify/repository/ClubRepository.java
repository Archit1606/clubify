package com.telusko.clubify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.clubify.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {}
