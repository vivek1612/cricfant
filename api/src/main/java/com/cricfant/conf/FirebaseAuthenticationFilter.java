package com.cricfant.conf;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.helper.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private static String HEADER_NAME = "X-Authorization-Firebase";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String xAuth = request.getHeader(HEADER_NAME);
        if (StringUtil.isBlank(xAuth)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(xAuth);
                String userName = firebaseToken.getUid();
                Authentication auth = new FirebaseAuthenticationToken(userName, firebaseToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } catch (FirebaseAuthException e) {
                log.debug(e.getErrorCode());
                throw new SecurityException(e);
            }
        }
    }


}
