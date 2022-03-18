package com.example.demo.controller;

import com.example.demo.rmi.inter.IClientRmiRemote;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class AuthentificationController
{
    IClientRmiRemote proxyClient;
    IManagerRmiRemote proxyManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public AuthentificationController(InMemoryUserDetailsManager inMemoryUserDetailsManager) throws RemoteException, NotBoundException, MalformedURLException
    {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        proxyClient = (IClientRmiRemote) Naming.lookup("rmi://localhost:1099/client");
        proxyManager = (IManagerRmiRemote) Naming.lookup("rmi://localhost:1099/manager");
    }

    @GetMapping("/login")
    public void login() throws RemoteException
    {
        System.out.println("LOGIN");

        proxyClient.findAllClients().forEach(client ->
        {
            if (!inMemoryUserDetailsManager.userExists(client.getUsername()))
            {
                inMemoryUserDetailsManager.createUser(new User(client.getUsername(), "{bcrypt}" + client.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT")))));
                System.out.println("*** new client : " + client.getUsername() + " ***");
            }
        });

        proxyManager.findAllManagers().forEach(manager ->
        {
            if (!inMemoryUserDetailsManager.userExists(manager.getUsername()))
            {
                inMemoryUserDetailsManager.createUser(new User(manager.getUsername(), "{bcrypt}" + manager.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))));
                System.out.println("*** new manager : " + manager.getUsername() + " ***");
            }
        });
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        // Le chemin "logout" est géré par défaut par Spring Security
        // Pou pouvoir utiliser cette methode il faut la lier à un autre chemin
        System.out.println("LOGOUT");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}