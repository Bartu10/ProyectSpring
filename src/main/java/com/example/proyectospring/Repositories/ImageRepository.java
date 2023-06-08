package com.example.proyectospring.Repositories;

import com.example.proyectospring.Models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
