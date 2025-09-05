package com.telusko.clubify.service;

 

import com.telusko.clubify.model.*;
import com.telusko.clubify.repository.BookingRepository;
import com.telusko.clubify.repository.ClubRepository;
import com.telusko.clubify.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepo;
    private final ClubRepository clubRepo;
    private final VenueRepository venueRepo;

    public BookingService(BookingRepository bookingRepo, ClubRepository clubRepo, VenueRepository venueRepo) {
        this.bookingRepo = bookingRepo;
        this.clubRepo = clubRepo;
        this.venueRepo = venueRepo;
    }

    public Booking requestBooking(Long clubId, Long venueId, LocalDateTime start, LocalDateTime end) {
        Club club = clubRepo.findById(clubId).orElseThrow();
        Venue venue = venueRepo.findById(venueId).orElseThrow();

        // Check conflicts
        List<Booking> conflicts = bookingRepo
                .findByVenueIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(
                        venueId, end, start, BookingStatus.APPROVED);

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Time slot already booked!");
        }

        Booking booking = new Booking();
        booking.setClub(club);
        booking.setVenue(venue);
        booking.setStartTime(start);
        booking.setEndTime(end);
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepo.save(booking);
    }

    public Booking approveBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.APPROVED);
        return bookingRepo.save(booking);
    }

    public Booking rejectBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.REJECTED);
        return bookingRepo.save(booking);
    }

    public List<Booking> getVenueSchedule(Long venueId) {
        return bookingRepo.findByVenueIdAndStatus(venueId, BookingStatus.APPROVED);
    }
}
