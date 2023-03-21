package com.codecool.dogmate.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareAnnouncementsTagId implements Serializable {

    private static final long serialVersionUID = 9106736410905014375L;
    @Column(name = "care_announcement_id")
    private Integer careAnnouncementId;

    @Column(name = "tag_name")
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CareAnnouncementsTagId entity = (CareAnnouncementsTagId) o;
        return Objects.equals(this.tagName, entity.tagName) &&
                Objects.equals(this.careAnnouncementId, entity.careAnnouncementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, careAnnouncementId);
    }

}
