package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "castmovie")
public class CastMovie {

    @Id
    @Column(name= "castid")
    private int castid;

    @Column(name = "movieid")
    private int movieid;
}
