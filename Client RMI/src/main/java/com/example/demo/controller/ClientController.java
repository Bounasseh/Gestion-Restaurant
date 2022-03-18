package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.rmi.inter.IClientRmiRemote;
import com.example.demo.rmi.inter.IFavoriteRestaurantRmiRemote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
public class ClientController
{
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    IClientRmiRemote proxyClient;
    IFavoriteRestaurantRmiRemote proxyFavoriteRestaurant;

    public ClientController(InMemoryUserDetailsManager inMemoryUserDetailsManager) throws RemoteException, NotBoundException, MalformedURLException
    {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        proxyClient = (IClientRmiRemote) Naming.lookup("rmi://localhost:1099/client");
        proxyFavoriteRestaurant = (IFavoriteRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/favoriteRestaurant");
    }

    @GetMapping("/client/home")
    public String homeClient(Model model, Principal principal) throws RemoteException
    {
        System.out.println("HOME_CLIENT");
        Client client = proxyClient.findByUsername(principal.getName());
        model.addAttribute("client", client);
        model.addAttribute("favoriteRestaurants", proxyClient.getFavoriteRestaurants(client));
        return "home_client";
    }

    @GetMapping("/client/edit")
    public String edit(Model model, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_FORM_CLIENT");
        Client client = proxyClient.findByUsername(principal.getName());
        model.addAttribute("client", client);
        model.addAttribute("currentUsername", client.getUsername());
        return "update_client";
    }

    @PostMapping("/client/edit")
    public String edit(@Valid Client client, BindingResult bindingResult, Model model, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_CLIENT");

        client.setId(proxyClient.findByUsername(principal.getName()).getId());
        client.setUsername(principal.getName());

        if (bindingResult.hasErrors())
        {
            return "update_client";
        }

        System.out.println("SAVE_CLIENT");
        proxyClient.editClient(client);

        inMemoryUserDetailsManager.deleteUser(principal.getName());
        inMemoryUserDetailsManager.createUser(new User(principal.getName(), "{noop}" + client.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT")))));

        System.out.println("REDIRECT_CLIENT");
        return "redirect:/client/home";
    }

    @PostMapping("/favrouriteRestaurant/removeFromHome/{favoriteRestaurantId}")
    String removeFavoriteRestaurantFromHome(@PathVariable Long favoriteRestaurantId, Principal principal) throws RemoteException
    {
        System.out.println("REMOVE_FAVORITE_RESTAURANT_FROM_HOME");
        proxyFavoriteRestaurant.removeFavoriteRestaurantById(proxyFavoriteRestaurant.findById(favoriteRestaurantId));
        return "redirect:/client/home";
    }
}