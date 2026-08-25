package com.EventHive.realtime.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EventHive.realtime.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
