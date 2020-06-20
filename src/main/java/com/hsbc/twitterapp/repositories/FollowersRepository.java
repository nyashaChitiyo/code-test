package com.hsbc.twitterapp.repositories;

import com.hsbc.twitterapp.entities.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowersRepository extends JpaRepository<Followers,Long> {

    Optional<Followers> findFollowersByFollowerId(long followersId);
}
