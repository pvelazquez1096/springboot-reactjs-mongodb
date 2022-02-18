package com.pvelazquez.springbootreactjsmongodb.repositories;

import com.pvelazquez.springbootreactjsmongodb.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<List<Client>> findClientsByName(String name);
    Optional<Client> findClientByEmail(String email);
}
