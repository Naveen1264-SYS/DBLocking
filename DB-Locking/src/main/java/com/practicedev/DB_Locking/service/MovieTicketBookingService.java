package com.practicedev.DB_Locking.service;

import com.practicedev.DB_Locking.entity.Seat;
import com.practicedev.DB_Locking.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieTicketBookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public Seat bookSeat (Long seatId){
        //1.fetch the existing seat by id
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(()->new RuntimeException("Seat not found with id"+ seatId));

        System.out.println(Thread.currentThread().getName()+"fetch seat with version "+seat.getVersion());
                if(seat.isBooked()){
                    throw new RuntimeException("Seat is Booked !");
                }
                //2.book seat
                seat.setBooked(true);
                //3.version check will occur here
                return  seatRepository.save(seat);


    }
    @Transactional
    public void bookSeatWithPessimistic(Long seatId){


        System.out.println(Thread.currentThread().getName()+"is attempting to fetch the seat");

        //1.fetch the seat with pessimistic lock
        Seat seat = seatRepository.findByIdAndLock(seatId);


        System.out.println(Thread.currentThread().getName()+"acquired the lock foe seat id "+seatId);
        if(seat.isBooked()){
            System.out.println(Thread.currentThread().getName()+"failed seatId"+ seatId+"is already Booked");
            throw new RuntimeException("Seat already  Booked !");
        }
        //2.book seat
        System.out.println(Thread.currentThread().getName()+"Booking the seat  !"+seatId);

        seat.setBooked(true);

        //3.version check will occur here
          seatRepository.save(seat);
        System.out.println(Thread.currentThread().getName()+"  Successfully booked the seat with Id  "+seatId);

}
}
