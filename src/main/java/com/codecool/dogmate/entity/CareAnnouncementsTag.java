package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "care_announcements_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareAnnouncementsTag {

    @EmbeddedId
    private CareAnnouncementsTagId id;

    @MapsId("careAnnouncementId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_announcement_id")
    private CareAnnouncement careAnnouncement;

    @MapsId("tagName")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_name")
    private Tag tagName;

}
