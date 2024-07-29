package org.itstep.springbootjava32.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curators")
public class Curator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;
    private String Surname;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "groupscurators",
            joinColumns = @JoinColumn(name = "Curator_Id"),
            inverseJoinColumns = @JoinColumn(name = "Group_Id"))
    private Set<Group> groups;


}
