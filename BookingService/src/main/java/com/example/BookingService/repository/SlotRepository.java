package com.example.BookingService.repository;

import com.example.BookingService.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
