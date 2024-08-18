package org.itstep.springbootjava32.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Lecture> lectures; // Связь с лекциями

    @OneToOne
    @JoinColumn(name = "lecture_room_id")
    private LectureRoom lectureRoom; // Связь с аудиторией
}

