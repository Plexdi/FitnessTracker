package com.plexdi.fitnesstrackerbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workout_routines")
public class WorkoutRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    @Column(nullable = false)
    private String routineName;

    @OneToMany(mappedBy = "workoutRoutine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users;
}
