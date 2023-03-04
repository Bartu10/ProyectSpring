package com.example.proyectospring.Repositories;


import com.example.proyectospring.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
