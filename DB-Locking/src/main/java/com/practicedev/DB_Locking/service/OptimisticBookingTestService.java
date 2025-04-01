package com.practicedev.DB_Locking.service;

import com.practicedev.DB_Locking.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptimisticBookingTestService {

    @Autowired
    private   MovieTicketBookingService movieTicketBookingService;

    public void  testOptimisticLocking(Long seatId) throws  InterruptedException{
        //2 thread
        Thread Ram = new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"is attempting to book the seat");
                Seat seat =movieTicketBookingService.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName()+"successfully booked the seat with version "+seat.getVersion());
            }catch (Exception ex) {
                System.out.println(Thread.currentThread().getName()+" failed  "+ ex.getMessage());
            }


        });
        Thread Shyam = new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"is attempting to book the seat");
                Seat seat =movieTicketBookingService.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName()+"successfully booked the seat with version "+seat.getVersion());
            }catch (Exception ex) {
                System.out.println(Thread.currentThread().getName()+" failed  "+ ex.getMessage());
            }
        });
        Ram.start();
        Shyam.start();
        Ram.join();
        Shyam.join();



    }
}
