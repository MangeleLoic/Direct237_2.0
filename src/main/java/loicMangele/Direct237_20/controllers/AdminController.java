package loicMangele.Direct237_20.controllers;


import loicMangele.Direct237_20.dto.AdminDTO;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.BadRequestException;
import loicMangele.Direct237_20.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // GET http://localhost:3001/admins
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Admin> getAllAdmins(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return adminService.getAllAdmins(page, size, sortBy);
    }

    // GET http://localhost:3001/admins/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.findAdminById(id);
    }



    // PUT http://localhost:3001/admins/{id} (+ req.body)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody AdminDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Dati non validi: " + message);
        }
        return adminService.updateAdmin(id, body);
    }

    // DELETE http://localhost:3001/admins/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
