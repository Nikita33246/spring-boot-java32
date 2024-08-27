package org.itstep.springbootjava32.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "lecture_rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "name", nullable = false)
    private String name;
}

