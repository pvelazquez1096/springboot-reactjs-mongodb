package com.pvelazquez.springbootreactjsmongodb.controllers;


import com.pvelazquez.springbootreactjsmongodb.models.Client;
import com.pvelazquez.springbootreactjsmongodb.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id){
        return clientService.getById(id);
    }

    @GetMapping("/byName/{name}")
    public Optional<List<Client>> getClientByName(@PathVariable String name){
        return clientService.getByName(name);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
         Client newClient = clientService.createClient(client);
         if(newClient != null)
            return ResponseEntity.created(new URI("/clients/" + newClient.getEmail())).body(newClient);
         else
            return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuario Already Exists");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@RequestBody Client client, @PathVariable String id){
        Client updatedClient =  clientService.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable String id){
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
