package org.itstep.springbootjava32.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
    @NotEmpty(message = "Field name must be not empty")
    private String name;
    private int rating;
    @NotEmpty(message = "Field surname must be not empty")
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
