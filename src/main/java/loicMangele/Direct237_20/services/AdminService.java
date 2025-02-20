package loicMangele.Direct237_20.services;



import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.AdminNotFoundByEmailException;
import loicMangele.Direct237_20.exceptions.AdminNotFoundByIdException;
import loicMangele.Direct237_20.exceptions.AdminNotFoundByNameException;
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



    public Admin createAdmin(Admin admin) {

        admin.setPassword(admin.getPassword());
        return adminRepo.save(admin);
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

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = findAdminById(id);
        existingAdmin.setNom(updatedAdmin.getNom());
        existingAdmin.setEmail(updatedAdmin.getEmail());


        if (!updatedAdmin.getPassword().equals(existingAdmin.getPassword())) {
            existingAdmin.setPassword(updatedAdmin.getPassword());
        }

        return adminRepo.save(existingAdmin);
    }


    public void deleteAdmin(Long id) {
        Admin admin = findAdminById(id);
        adminRepo.delete(admin);
    }
}