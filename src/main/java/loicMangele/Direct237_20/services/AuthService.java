package loicMangele.Direct237_20.services;

import loicMangele.Direct237_20.dto.AdminLoginDto;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.UnauthorizedException;
import loicMangele.Direct237_20.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JWT jwt;

    public String CheckCredentialsAndGenerate(AdminLoginDto body){
        Admin found = this.adminService.findAdminByEmail(body.email());

        if(found.getPassword().equals(body.password())){
String accessToken = jwt.createToken(found);
return accessToken;
        }else {
throw new UnauthorizedException("Credenziali errate");
        }

    }
}
