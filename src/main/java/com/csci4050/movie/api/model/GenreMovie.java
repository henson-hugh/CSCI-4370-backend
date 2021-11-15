package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "genremovie")
public class GenreMovie {

    @Id
    @Column(name= "genreid")
    private int genreid;

    @Column(name = "movieid")
    private int movieid;
}
