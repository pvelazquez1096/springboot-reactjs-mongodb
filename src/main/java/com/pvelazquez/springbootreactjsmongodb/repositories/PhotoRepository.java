package com.pvelazquez.springbootreactjsmongodb.repositories;

import com.pvelazquez.springbootreactjsmongodb.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
