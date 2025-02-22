package loicMangele.Direct237_20.controllers;

import loicMangele.Direct237_20.dto.AdminDTO;
import loicMangele.Direct237_20.dto.AdminLoginDto;
import loicMangele.Direct237_20.dto.AdminResponseLoginDTO;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.BadRequestException;
import loicMangele.Direct237_20.services.AdminService;
import loicMangele.Direct237_20.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AdminResponseLoginDTO login (@RequestBody AdminLoginDto body) {
         return new AdminResponseLoginDTO(this.authService.CheckCredentialsAndGenerate(body)) ;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody @Validated AdminDTO body, BindingResult validationResult){
        if ((validationResult.hasErrors())) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return  this.adminService.createAdmin(body);
    }
}
