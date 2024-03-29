package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Size(min = 5, max = 50)
    private String name;

    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "teryt_id", unique = true)
    private String terytId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voivodeship_id")
    @NotNull
    private Voivodeship voivodeship;

    @Column(name = "archive")
    private Boolean archive = false;

    @Version
    private Integer version;

    @Column(name = "date_create")
    private LocalDateTime date_create = LocalDateTime.now();;

    @Column(name = "date_modify")
    private LocalDateTime date_modify ;

    @Column(name = "date_archive")
    private LocalDateTime date_archive ;

    @OneToMany(mappedBy = "province", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<City> cities = new HashSet<>();

    public Province(String name, String terytId) {
        this.name = name;
        this.terytId = terytId;
    }
}
