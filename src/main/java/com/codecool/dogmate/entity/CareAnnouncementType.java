package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "care_announcement_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CareAnnouncementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
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

    public CareAnnouncementType(String name) {
        this.name = name;
    }
}
