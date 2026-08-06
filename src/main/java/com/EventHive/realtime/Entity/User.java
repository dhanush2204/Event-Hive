package com.EventHive.realtime.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name="user_id")
    private String user_id;
    private String user_name;
    private String email;
    private String password_hash;
    private String created_at;
    @OneToMany(mappedBy="user")
    private List<Booking> bookings;
}
