package com.project.fitness;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

}
