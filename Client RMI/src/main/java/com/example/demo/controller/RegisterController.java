package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Manager;
import com.example.demo.rmi.inter.IClientRmiRemote;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import com.example.demo.rmi.inter.IUserRmiRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@Controller
public class RegisterController
{
    IUserRmiRemote proxyUser;
    IClientRmiRemote proxyClient;
    IManagerRmiRemote proxyManager;

    @Autowired
    public RegisterController() throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyUser = (IUserRmiRemote) Naming.lookup("rmi://localhost:1099/user");
        proxyClient = (IClientRmiRemote) Naming.lookup("rmi://localhost:1099/client");
        proxyManager = (IManagerRmiRemote) Naming.lookup("rmi://localhost:1099/manager");
    }

    @GetMapping("/signup")
    public String signup(Manager manager, Model model)
    {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid Manager manager, BindingResult bindingResult, @RequestParam String role, Model model) throws RemoteException
    {
        if (bindingResult.hasErrors() || proxyUser.isUsernameUsed(manager.getUsername()))
        {
            if (proxyUser.isUsernameUsed(manager.getUsername()))
                model.addAttribute("usernameUsed", true);

            return "signup";
        }

        switch (role)
        {
            case "client":
            {
                Client client = new Client(null, manager.getUsername(), manager.getEmail(), manager.getPassword(), null, null);
                proxyClient.newClient(client);
                break;
            }
            case "manager":
            {
                proxyManager.newManager(manager);
                break;
            }
        }

        return "redirect:/login";
    }
}
