package com.EventHive.realtime.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.EventHive.realtime.Enum.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable=false)
    private UserRole role;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy="user")
    private List<Booking> bookings;
}
