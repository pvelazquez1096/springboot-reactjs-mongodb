package com.pvelazquez.springbootreactjsmongodb.services;

import com.pvelazquez.springbootreactjsmongodb.models.Photo;
import com.pvelazquez.springbootreactjsmongodb.repositories.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title, new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        photo = photoRepository.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(String id){
        return photoRepository.findById(id).get();
    }
}
