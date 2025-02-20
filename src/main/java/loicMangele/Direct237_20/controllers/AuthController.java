package loicMangele.Direct237_20.controllers;

import loicMangele.Direct237_20.dto.AdminLoginDto;
import loicMangele.Direct237_20.dto.AdminResponseLoginDTO;
import loicMangele.Direct237_20.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public AdminResponseLoginDTO login(@RequestBody AdminLoginDto body){
        return new AdminResponseLoginDTO(this.authService.CheckCredentialsAndGenerate(body)) ;
    }
}
