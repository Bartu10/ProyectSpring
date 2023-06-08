package com.example.proyectospring.Controller;

import com.example.proyectospring.Models.Image;
import com.example.proyectospring.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@Repository
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/images/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image)
            throws IOException {
        return imageService.addImage(title, image);
    }

    @GetMapping("/images/{id}")
    public String getPhoto(@PathVariable String id) {
        Image image = imageService.getImage(id);
        return "\"data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(image.getImage().getData()) + "\"";
    }
}