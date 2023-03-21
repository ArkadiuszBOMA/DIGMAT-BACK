package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "care_announcements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "care_announcement_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_announcement_type_id")
    private CareAnnouncementType careAnnouncementType;

    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_unit_id")
    private TimeUnit timeUnit;

    @OneToMany(mappedBy = "careAnnouncement")
    private Set<Review> reviews = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "care_announcements_tags",
            joinColumns = @JoinColumn(name = "care_announcement_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name"))
    private Set<Tag> tags = new LinkedHashSet<>();

}
