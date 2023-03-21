package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    private String first_name;
    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @Lob
    @Column(name = "profile_picture_location")
    private String profilePictureLocation;

    @Lob
    @Column(name = "avatar_small_location")
    private String avatarSmallLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

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

    public AppUser(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }
}
