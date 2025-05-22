package practice.buttersaltflour.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import practice.buttersaltflour.filter.exception.FirebaseAuthFailureException;
import practice.buttersaltflour.filter.exception.FirebaseAuthHeaderMissingException;
import practice.buttersaltflour.filter.exception.FirebaseAuthInvalidFormatException;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FirebaseAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info(">>> FirebaseAuthFilter 동작 시작");
        String header = request.getHeader("Authorization");

        if (header == null) {
            throw new FirebaseAuthHeaderMissingException();
        }

        if (!header.startsWith("Bearer ")) {
            throw new FirebaseAuthInvalidFormatException();
        }

        String token = header.substring(7);
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            log.info(">>> 인증 성공: " + uid);

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

            Authentication auth = new UsernamePasswordAuthenticationToken(decodedToken, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthFailureException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
