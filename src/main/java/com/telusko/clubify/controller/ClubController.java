package com.telusko.clubify.controller;

 

import com.telusko.clubify.model.Club;
import com.telusko.clubify.repository.ClubRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 
@RestController
@RequestMapping("/clubs")
public class ClubController {
    private final ClubRepository repo;
    public ClubController(ClubRepository repo) { this.repo = repo; }

    @PostMapping
    public Club addClub(@RequestBody Club club) {
        return repo.save(club);
    }

    @GetMapping
    public List<Club> getClubs() {
        return repo.findAll();
    }
}
