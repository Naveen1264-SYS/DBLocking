package com.practicedev.DB_Locking.controller;

import com.practicedev.DB_Locking.service.OptimisticBookingTestService;
import com.practicedev.DB_Locking.service.PessimisticSeatBookingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private OptimisticBookingTestService optimisticBookingTestService;

    @Autowired
    private PessimisticSeatBookingTestService pessimisticSeatBookingTestService;

    @GetMapping("/optimistic/{seatId}")
    public String testOptimistic(@PathVariable Long seatId) throws InterruptedException {
       optimisticBookingTestService.testOptimisticLocking(seatId);
        return  "Optimistic locking test started! Check logs for results. ";
    }

    @GetMapping("/pessimistic/{seatId}")
    public String testPessimistic(@PathVariable Long seatId) throws InterruptedException {
        pessimisticSeatBookingTestService.testPessimisticLocking(seatId);
        return  "Pessimistic Locking test started! Check logs for results.";
    }

}
