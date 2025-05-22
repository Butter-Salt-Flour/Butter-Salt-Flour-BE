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
import practice.buttersaltflour.auth.entity.CustomPrincipal;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FirebaseAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,

                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.equals("/v3/api-docs.yaml")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            log.info(">>> Authorization 헤더가 없거나 형식이 잘못됨 → 필터 패스");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String displayName = decodedToken.getName();

            log.info(">>> 인증 성공: " + uid);

            // ✅ CustomPrincipal 생성
            CustomPrincipal principal = new CustomPrincipal(uid, email, displayName);

            // ✅ 권한 설정
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

            // ✅ SecurityContext에 등록
            Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (FirebaseAuthException e) {
            throw new FirebaseAuthFailureException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
