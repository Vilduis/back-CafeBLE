package backend.project.serviceimpl;

import backend.project.dtos.DTOCliente;
import backend.project.entities.Client;
import backend.project.entities.User;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.services.ClientService;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserService userService;

    @Override
    public Client insertClient(DTOCliente dtoClient)
    {
        User user = (dtoClient.getUserId() != null) ? userService.findById(dtoClient.getUserId()) : null;

        Client client = new Client();
        client.setUser(user);
        client.setFirstName(dtoClient.getFirstName());
        client.setLastName(dtoClient.getLastName());
        client.setPhone(dtoClient.getPhone());
        client.setAddress(dtoClient.getAddress());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(DTOCliente dtoClient)
    {
        Client clientFound = clientRepository.findById(dtoClient.getId()).orElse(null);
        if(clientFound==null)
        {
            throw  new ResourceNotFoundException("Client with id:"+dtoClient.getId()+"can not be found");
        }
        clientFound.setFirstName(dtoClient.getFirstName());
        clientFound.setLastName(dtoClient.getLastName());
        clientFound.setPhone(dtoClient.getPhone());
        clientFound.setAddress(dtoClient.getAddress());
        return clientRepository.save(clientFound);
    }

    @Override
    public Client findById(Long id)
    {
        Client clientFound = clientRepository.findById(id).orElse(null);
        if(clientFound==null)
        {
            throw  new ResourceNotFoundException("Client with id:"+id+"can not be found");
        }
        return clientFound;
    }

    @Override
    public void deleteClient(Long id)
    {
        Client clientFound = clientRepository.findById(id).orElse(null);
        if(clientFound==null)
        {
            throw  new ResourceNotFoundException("Client with id:"+id+"can not be found");
        }
        clientRepository.delete(clientFound);
    }
    @Override
    public List<Client> listAllClient()
    {
        return clientRepository.findAll();
    }
}
