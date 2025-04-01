package com.practicedev.DB_Locking.repository;

import com.practicedev.DB_Locking.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;


public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM  Seat s WHERE s.id= :seatId")
    Seat  findByIdAndLock(Long seatId);
}
// Types of locks avaliable
//READ,
//WRITE,
//OPTIMISTIC,
//OPTIMISTIC_FORCE_INCREMENT,
//PESSIMISTIC_READ,
//PESSIMISTIC_WRITE,
//PESSIMISTIC_FORCE_INCREMENT,
//NONE;
//--FOR UPDATE WE CAN USE PESSIMISTIC-write
