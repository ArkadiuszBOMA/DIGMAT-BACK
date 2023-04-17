package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_privileges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "name", unique = true)
    private String name;

    @Version
    private Integer version;

    @Column(name = "archive")
    private Boolean archive = false;

    @Column(name = "date_create")
    private LocalDateTime date_create = LocalDateTime.now();

    @Column(name = "date_modify")
    private LocalDateTime date_modify ;

    @Column(name = "date_archive")
    private LocalDateTime date_archive ;

    @ManyToMany(mappedBy = "userPrivileges", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<UserRole> userRoles = new HashSet<>();


    public UserPrivilege(String name) {
        this.name = name;
    }
}
