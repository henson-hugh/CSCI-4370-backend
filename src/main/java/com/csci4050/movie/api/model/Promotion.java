package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int pid;

    @Column(name = "discount")
    private double discount;

    @Column(name = "pcode", unique = true)
    private String pcode;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;
}
