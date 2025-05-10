package backend.project.controllers;

import backend.project.dtos.DTOCliente;
import backend.project.entities.Client;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClient()
    {
        return ResponseEntity.ok(clientService.listAllClient());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id)
    {
        Client client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/clients/register")
    public ResponseEntity<Client> register(@RequestBody DTOCliente dtoClient)
    {
        Client client = clientService.insertClient(dtoClient);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody DTOCliente dtoClient)
    {
        Client client = clientService.updateClient(dtoClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id)
    {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
