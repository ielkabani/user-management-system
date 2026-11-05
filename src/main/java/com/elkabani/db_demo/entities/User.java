package com.elkabani.db_demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="id")
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="email")
    private String email;

    @Column(nullable = false, name="password")
    private String password;

    @Column(nullable = false, name ="active")
   @Builder.Default
    private Boolean active = true;


    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy="user", cascade = CascadeType.REMOVE)
    private Profile profile;

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address)
    {
        addresses.remove(address);
        address.setUser(null);
    }
}
