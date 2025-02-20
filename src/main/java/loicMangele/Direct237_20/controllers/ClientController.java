package loicMangele.Direct237_20.controllers;


import loicMangele.Direct237_20.entities.Client;
import loicMangele.Direct237_20.exceptions.BadRequestException;
import loicMangele.Direct237_20.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // GET http://localhost:3001/clients
    @GetMapping
    public Page<Client> getAllClients(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return clientService.findAllClients(page, size, sortBy);
    }

    // GET http://localhost:3001/clients/{id}
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    // POST http://localhost:3001/clients (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody  Client client, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errore nel payload: " + message);
        }
        return clientService.createClient(client);
    }

    // PUT http://localhost:3001/clients/{id} (+ req.body)
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody  Client updatedClient, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errore nel payload: " + message);
        }
        return clientService.updateClient(id, updatedClient);
    }

    // DELETE http://localhost:3001/clients/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
