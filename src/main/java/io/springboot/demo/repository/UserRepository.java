package io.springboot.demo.repository;

import org.springframework.stereotype.Repository;

import io.springboot.demo.entity.User;


@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

}
