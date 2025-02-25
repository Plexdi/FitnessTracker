package com.plexdi.fitnesstrackerbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Generates a UUID for primary key
    private String id;

    @ManyToOne // Assuming a User has one WorkoutRoutine
    @JoinColumn(name = "workout_routine_id") // Foreign key reference
    private WorkoutRoutine workoutRoutine;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @jakarta.validation.constraints.Email
    private String email;

    @Column(nullable = false)
    private LocalDate dob; // Use LocalDate instead of String for date handling

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(nullable = false)
    private LocalDate updatedAt = LocalDate.now();
}
