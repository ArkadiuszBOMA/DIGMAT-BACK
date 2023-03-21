package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "userType", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<AppUser> appUsers = new HashSet<>();

    public UserType(String name) {
        this.name = name;
    }
}
