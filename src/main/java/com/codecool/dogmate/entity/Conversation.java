package com.codecool.dogmate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_sender_id")
    private AppUser originalSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_receiver_id")
    private AppUser originalReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "care_announcement_id")
    private CareAnnouncement careAnnouncement;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages = new LinkedHashSet<>();

}
