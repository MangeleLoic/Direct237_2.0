package loicMangele.Direct237_20.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import loicMangele.Direct237_20.entities.Admin;
import loicMangele.Direct237_20.exceptions.UnauthorizedException;
import loicMangele.Direct237_20.services.AdminService;
import loicMangele.Direct237_20.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTChecker extends OncePerRequestFilter {

    @Autowired
    private JWT jwt;
    @Autowired
    private AdminService adminService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Inserire token nell'Authorization Header nel formato corretto!");
        }
        String accessToken = authHeader.substring(7);

        jwt.verifyToken(accessToken);

String adminId = jwt.getIdFromToken(accessToken);
        Admin currentAdmin = this.adminService.findAdminById(Long.parseLong(adminId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentAdmin, null,currentAdmin.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
