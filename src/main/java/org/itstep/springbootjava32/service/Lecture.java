package org.itstep.springbootjava32.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String lecturer;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule; // Связь с расписанием
}

