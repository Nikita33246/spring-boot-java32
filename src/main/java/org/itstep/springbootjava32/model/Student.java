package org.itstep.springbootjava32.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    private int rating;
    private String surname;


    @ManyToOne
    @JoinColumn(name = "Group_id")
    private Group group;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "studentteacher",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
//    private Set<Teacher> teachers;


}
