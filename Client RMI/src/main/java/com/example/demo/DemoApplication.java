package com.example.demo;

import com.example.demo.rmi.inter.IAppInitRmiRemote;
import com.example.demo.rmi.inter.IClientRmiRemote;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{
    private static IAppInitRmiRemote proxyAppInit;
    private IClientRmiRemote proxyClient;
    private IManagerRmiRemote proxyManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public DemoApplication(InMemoryUserDetailsManager inMemoryUserDetailsManager) throws RemoteException, NotBoundException, MalformedURLException
    {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        proxyClient = (IClientRmiRemote) Naming.lookup("rmi://localhost:1099/client");
        proxyManager = (IManagerRmiRemote) Naming.lookup("rmi://localhost:1099/manager");
    }

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    public void loadAllUsers() throws RemoteException
    {
        System.out.println("DemoApplication");

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

    @Override
    public void run(String... args) throws RemoteException, NotBoundException, MalformedURLException
    {
        //proxyUser = (IUserRmiRemote) Naming.lookup("rmi://localhost:1099/user");
        proxyAppInit = (IAppInitRmiRemote) Naming.lookup("rmi://localhost:1099/appInit");

        proxyAppInit.deleteData();
        System.out.println("Data is deleted successfully");
        proxyAppInit.insertData();
        System.out.println("Data is inserted successfully");
        loadAllUsers();
    }
}