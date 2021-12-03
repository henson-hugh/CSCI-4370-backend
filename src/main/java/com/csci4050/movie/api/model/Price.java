package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "prices")
public class Price {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int pid;

    @Column(name= "aTicket")
    private int aTicket;

    @Column(name= "cTicket")
    private int cTicket;

    @Column(name= "sTicket")
    private int sTicket;

    @Column(name= "fee")
    private int fee;
}
