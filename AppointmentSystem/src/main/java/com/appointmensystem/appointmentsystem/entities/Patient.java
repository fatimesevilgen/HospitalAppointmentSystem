package com.appointmensystem.appointmentsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "first_name")
    private String firstName ;

    @Column(name = "last_name")
    private String lastName ;

    @Column(name = "user_name")
    private String userName ;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age ;

    @Column(name = "hashed_password")
    private String hashedPassword ;
}
