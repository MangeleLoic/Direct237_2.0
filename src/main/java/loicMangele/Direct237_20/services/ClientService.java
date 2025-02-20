package loicMangele.Direct237_20.services;


import loicMangele.Direct237_20.entities.Client;
import loicMangele.Direct237_20.exceptions.ClientNotFoundByIdException;
import loicMangele.Direct237_20.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public Client createClient (Client client) {
        return clientRepo.save(client);
    }

    public Client findById (Long id) {
        return clientRepo.findById(id).orElseThrow(() ->new ClientNotFoundByIdException("Pas de Clients trouvés avec cet id: " + id));
    }

    public Page<Client> findAllClients(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page -1, size, Sort.by(sortBy));
        return clientRepo.findAll(pageable);
    }

    public Client updateClient(Long id,Client updatedClient) {
        Client excistingClient = findById(id);
        excistingClient.setNomClient(updatedClient.getNomClient());
        excistingClient.setNumeroTelephone(updatedClient.getNumeroTelephone());
        return clientRepo.save(excistingClient);
    }

    public void deleteClient(Long id) {
        Client client = findById(id);
        clientRepo.delete(client);
    }

}
