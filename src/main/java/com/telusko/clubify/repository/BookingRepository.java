package com.telusko.clubify.repository;

 

import com.telusko.clubify.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByVenueIdAndStatus(Long venueId, BookingStatus status);

    List<Booking> findByVenueIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(
            Long venueId, LocalDateTime endTime, LocalDateTime startTime, BookingStatus status);
}
