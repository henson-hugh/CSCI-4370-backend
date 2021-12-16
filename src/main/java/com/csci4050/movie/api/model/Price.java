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

    @Column(name= "aticket")
    private int aticket;

    @Column(name= "cticket")
    private int cticket;

    @Column(name= "sticket")
    private int sticket;

    @Column(name= "fee")
    private int fee;
}
