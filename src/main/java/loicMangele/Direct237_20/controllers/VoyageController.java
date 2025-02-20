package loicMangele.Direct237_20.controllers;


import loicMangele.Direct237_20.entities.Voyage;
import loicMangele.Direct237_20.exceptions.BadRequestException;
import loicMangele.Direct237_20.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/voyages")
public class VoyageController {
    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public Page<Voyage> getAllVoyages(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return voyageService.getAllVoyages(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Voyage getVoyageById(@PathVariable Long id) {
        return voyageService.findVoyageById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Voyage createVoyage( @RequestBody Voyage voyage, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Erreur dans le payload: " + message);
        }
        return voyageService.createVoyage(voyage);
    }

    @PutMapping("/{id}")
    public Voyage updateVoyage(@PathVariable Long id, @RequestBody Voyage updatedVoyage, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Erreur dans le payload: " + message);
        }
        return voyageService.updateVoyage(id, updatedVoyage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
    }
}
