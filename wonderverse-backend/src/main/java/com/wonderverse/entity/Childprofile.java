package com.wonderverse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "child_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    private String avatar;

    @Column(name = "screen_time_limit")
    private Integer screenTimeLimit = 2;
}