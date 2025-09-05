package com.telusko.clubify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.clubify.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {}
