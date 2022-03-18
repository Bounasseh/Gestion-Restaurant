package com.example.demo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class TempController
{
    @GetMapping("/session")
    @ResponseBody
    public List session(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username = context.getAuthentication().getName();
        Collection<? extends GrantedAuthority> grantedAuthorities = context.getAuthentication().getAuthorities();
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthoritie : grantedAuthorities)
        {
            authorities.add(grantedAuthoritie.getAuthority());
        }
        List<List<String>> params = new ArrayList<>();
        params.add(List.of(username));
        params.add(authorities);
        return params;
    }
}
