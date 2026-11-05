package com.elkabani.db_demo.entities;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="street", nullable = false)
    private String street;
    @Column(name="city", nullable = false)
    private String city;
    @Column(name="state", nullable = false)
    private String state;
    @Column(name="zip", nullable = false)
    private String zipCode;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;
}
