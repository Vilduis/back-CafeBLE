package backend.project.services;

import backend.project.dtos.DTOCliente;
import backend.project.entities.Client;

import java.util.List;

public interface ClientService {
    public Client insertClient(DTOCliente dtoClient);
    public Client updateClient(DTOCliente dtoClient);
    public Client findById(Long id);
    public void deleteClient(Long id);
    public List<Client> listAllClient();
}
