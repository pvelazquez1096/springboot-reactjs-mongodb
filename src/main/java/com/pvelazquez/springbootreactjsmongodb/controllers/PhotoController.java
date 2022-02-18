package com.pvelazquez.springbootreactjsmongodb.controllers;

import com.pvelazquez.springbootreactjsmongodb.models.Photo;
import com.pvelazquez.springbootreactjsmongodb.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @GetMapping("/{id}")
    public ResponseEntity getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity addPhoto(@RequestParam("title") String title,
                                   @RequestBody MultipartFile image)
            throws IOException {
        if(image != null) {
            System.out.println(image);
            String id = photoService.addPhoto(title, image);
            return ResponseEntity.ok(id);
        }else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
