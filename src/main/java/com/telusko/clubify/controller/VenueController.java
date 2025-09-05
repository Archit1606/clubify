package com.telusko.clubify.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.clubify.model.Venue;
import com.telusko.clubify.repository.VenueRepository;

@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueRepository repo;
    public VenueController(VenueRepository repo) { this.repo = repo; }

    @PostMapping
    public Venue addVenue(@RequestBody Venue venue) {
        return repo.save(venue);
    }

    @GetMapping
    public List<Venue> getVenues() {
        return repo.findAll();
    }
}
