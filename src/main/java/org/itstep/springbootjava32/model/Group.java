package org.itstep.springbootjava32.model;

import java.util.Set;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@Entity
@Table(name = "groupz")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int year;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "Department_Id")
    private Department department;

    @ManyToMany(mappedBy = "groups")
    private Set<Lecture> lectures;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "groupscurators",
            joinColumns = @JoinColumn(name = "Group_Id"),
            inverseJoinColumns = @JoinColumn(name = "Curator_Id"))
    private Set<Curator> curators;

}
