package loicMangele.Direct237_20.services;



import loicMangele.Direct237_20.dto.AdminDTO;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.*;
import loicMangele.Direct237_20.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;



    public Admin createAdmin(AdminDTO body) {
        this.adminRepo.findAdminByNom(body.nom()).ifPresent(existingAdmin -> {
            throw new BadRequestException("Le nom " + body.nom() + " est dejà utilisé");
        });

        this.adminRepo.findAdminByEmail(body.email()).ifPresent(
                existingUser -> {
                    throw new BadRequestException("L'adress email " + body.email() + " est dejà utilisé");
                }
        );

        Admin admin = new Admin();
        admin.setNom(body.nom());
        admin.setEmail(body.email());
        admin.setPassword(body.password());

        return this.adminRepo.save(admin);
    }


    public Admin findAdminByEmail(String email) {
        return this.adminRepo.findAdminByEmail(email).orElseThrow(()-> new AdminNotFoundByEmailException("Aucun Admin trouvé avec cet e-mail: " + email));
    }

    public Admin findAdminById(Long id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new AdminNotFoundByIdException("Aucun admin trouvé avec cet ID: " + id));
    }

    public Admin findAdminByNom(String nom) {
        return adminRepo.findAdminByNom(nom)
                .orElseThrow(() -> new AdminNotFoundByNameException("L'admin " + nom + " n'existe pas"));
    }

    public Page<Admin> getAllAdmins(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page -1, size, Sort.by(sortBy));
        return adminRepo.findAll(pageable);
    }

    public Admin updateAdmin(Long id, AdminDTO body) {
        return adminRepo.findById(id).map(admin -> {
            if (body.nom() != null) admin.setNom(body.nom());
            if (body.email() != null) admin.setEmail(body.email());
            if (body.password() != null && !body.password().isBlank()) {
                admin.setPassword(body.password());
            }


            return adminRepo.save(admin);
        }).orElseThrow(() -> new AdminNotFoundException(id));
    }



    public void deleteAdmin(Long id) {
        Admin admin = findAdminById(id);
        adminRepo.delete(admin);
    }
}