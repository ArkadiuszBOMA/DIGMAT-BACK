package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "appusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 5, max = 50)
    private String first_name;
    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "profile_picture_location")
    private String profilePictureLocation;

    @Column(name = "avatar_small_location")
    private String avatarSmallLocation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "description")
    private String description;

    @Column(name = "is_locked")
    private Boolean isLocked = false;

    @Column(name = "is_banned")
    private Boolean isBanned = false;

    @Column(name = "ban_expiration")
    private OffsetDateTime banExpiration;

    @Column(name = "is_active")
    private Boolean isActive = true ;


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

    @OneToMany(mappedBy = "appUser")
    private Set<Animal> animals = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="appuser_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "user_role_id",
                    referencedColumnName = "id"))
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addUserRole(UserRole r) {
        userRoles.add(r);
    }
}
