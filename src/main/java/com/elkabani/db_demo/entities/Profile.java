package com.elkabani.db_demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ColumnDefault("'0'")
    @Column(name = "loyalty_points", columnDefinition = "int UNSIGNED")
    private Integer loyaltyPoints;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    @MapsId
    private User user;
}