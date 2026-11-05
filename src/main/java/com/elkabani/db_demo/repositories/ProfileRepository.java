package com.elkabani.db_demo.repositories;

import com.elkabani.db_demo.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}