package com.example.demo.controller;

import com.example.demo.model.Manager;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import com.example.demo.rmi.inter.IUserRmiRemote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
public class ManagerController
{
    IManagerRmiRemote proxyManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public ManagerController(InMemoryUserDetailsManager inMemoryUserDetailsManager) throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyManager = (IManagerRmiRemote) Naming.lookup("rmi://localhost:1099/manager");
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @GetMapping("/manager/home")
    public String homeManager(Model model, Principal principal) throws RemoteException
    {
        System.out.println("HOME_MANAGER");
        Manager manager = proxyManager.findByUsername(principal.getName());
        model.addAttribute("manager", manager);
        model.addAttribute("restaurants", manager.getRestaurants());
        return "home_manager";
    }

    @GetMapping("/manager/edit")
    public String edit(Model model, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_FORM_MANAGER");
        Manager manager = proxyManager.findByUsername(principal.getName());
        model.addAttribute("manager", manager);
        model.addAttribute("currentUsername", manager.getUsername());
        return "update_manager";
    }

    @PostMapping("/manager/edit")
    public String edit(@Valid Manager manager, BindingResult bindingResult, Model model, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_MANAGER");

        manager.setId(proxyManager.findByUsername(principal.getName()).getId());
        manager.setUsername(principal.getName());

        if (bindingResult.hasErrors())
        {
            return "update_manager";
        }

        System.out.println("SAVE_MANAGER");
        proxyManager.editManager(manager);

        inMemoryUserDetailsManager.deleteUser(principal.getName());
        inMemoryUserDetailsManager.createUser(new User(principal.getName(), "{noop}" + manager.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))));

        System.out.println("REDIRECT_MANAGER");
        return "redirect:/manager/home";
    }
}