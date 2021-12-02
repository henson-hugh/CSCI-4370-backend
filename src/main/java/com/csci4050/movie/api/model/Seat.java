package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;

    @Column(name = "seatName")
    private boolean seatName;

    @Column(name = "showroomId")
    private int showroomId;

    @Column(name = "showingDate")
    private LocalDate showingDate;

    @Column(name = "showingTime")
    private LocalTime showingTime;

}