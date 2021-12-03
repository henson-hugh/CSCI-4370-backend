package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @Column(name= "bookingId")
    private LocalTime bookingId;

    @Column(name= "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name= "seatId")
    private int seatId;


}
