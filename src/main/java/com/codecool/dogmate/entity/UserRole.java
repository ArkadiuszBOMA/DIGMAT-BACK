package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "archive")
    private Boolean archive = false;

    @Version
    private Integer version;

    @Column(name = "date_create")
    private LocalDateTime date_create = LocalDateTime.now();

    @Column(name = "date_modify")
    private LocalDateTime date_modify ;

    @Column(name = "date_archive")
    private LocalDateTime date_archive ;

    @ManyToMany(mappedBy = "userRoles", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<AppUser> appUsers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="user_role_privilege",
            joinColumns = @JoinColumn(name="user_privilege_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="user_role_id",
                    referencedColumnName = "id"))
    private Set<UserPrivilege> userPrivileges = new LinkedHashSet<>();

    public UserRole(String name) {
        this.name = name;
    }

    public void addAppUser(AppUser r) {
        appUsers.add(r);
    }
}
