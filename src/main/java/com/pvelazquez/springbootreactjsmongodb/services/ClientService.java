package com.pvelazquez.springbootreactjsmongodb.services;

import com.pvelazquez.springbootreactjsmongodb.models.Client;
import com.pvelazquez.springbootreactjsmongodb.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return  clientRepository.findAll();
    }

    public Client getById(String _id){
        return  clientRepository.findById(_id).orElseThrow(RuntimeException::new);
    }

    public Optional<List<Client>> getByName(String name){
        return clientRepository.findClientsByName(name);
    }

    public Client createClient(Client client){
        boolean isClientPresent = clientRepository.findClientByEmail(client.getEmail()).isPresent();
        if(isClientPresent)
            return null;
        return clientRepository.insert(client);
    }

    public Client updateClient(String _id, Client client){
        Client updatedClient = clientRepository.findById(_id).orElseThrow(RuntimeException::new);
        updatedClient.setName(client.getName());
        updatedClient.setEmail(client.getEmail());
        updatedClient.setPhotoId(client.getPhotoId());
        return  clientRepository.save(updatedClient);
    }

    public void deleteClient(String _id){
        clientRepository.deleteById(_id);
    }
}
