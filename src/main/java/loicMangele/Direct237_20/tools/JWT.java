package loicMangele.Direct237_20.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Admin admin){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject(String.valueOf(admin.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))  
                .compact();
    }

    public void verifyToken(String accessToken){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(accessToken);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token! Per favore effettua di nuovo il login!");
        }
    }
}
