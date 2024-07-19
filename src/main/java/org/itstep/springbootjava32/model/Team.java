package org.itstep.springbootjava32.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Integer id;
    private String name;
    private String date;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    Set<Student> students;



    public Team(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
