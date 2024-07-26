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
@Table(name = "groupscurators")
public class GroupCurator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer Curator_Id;
    private Integer Group_Id;

    @OneToMany(mappedBy = "groupscurators")
    @JoinColumn(name = "Group_id")

    Set<Group> groups;

    @JoinColumn(name = "Curator_Id")

    private Curator group;



}
