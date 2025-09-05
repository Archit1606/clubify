package com.telusko.clubify.controller;

 

import com.telusko.clubify.model.Booking;
import com.telusko.clubify.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/request")
    public Booking requestBooking(@RequestParam Long clubId,
                                  @RequestParam Long venueId,
                                  @RequestParam String startTime,
                                  @RequestParam String endTime) {
        return bookingService.requestBooking(clubId, venueId,
                LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }

    @PutMapping("/{id}/approve")
    public Booking approveBooking(@PathVariable Long id) {
        return bookingService.approveBooking(id);
    }

    @PutMapping("/{id}/reject")
    public Booking rejectBooking(@PathVariable Long id) {
        return bookingService.rejectBooking(id);
    }

    @GetMapping("/venue/{venueId}")
    public List<Booking> getVenueSchedule(@PathVariable Long venueId) {
        return bookingService.getVenueSchedule(venueId);
    }
}
