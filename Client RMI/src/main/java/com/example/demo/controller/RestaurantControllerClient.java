package com.example.demo.controller;

import com.example.demo.rmi.inter.IRestaurantRmiRemote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@Controller
@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
public class RestaurantControllerClient
{
    IRestaurantRmiRemote proxyRestaurant;

    RestaurantControllerClient() throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyRestaurant = (IRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/restaurant");
    }

    @GetMapping("/client/restaurants/{restaurantId}")
    public String homeRestaurantFromClient(@PathVariable Long restaurantId, Model model) throws RemoteException
    {
        System.out.println("HOME_RESTAURANT_FROM_CLIENT");
        model.addAttribute("restaurant", proxyRestaurant.findById(restaurantId));
        return "home_restaurant_client";
    }

    @GetMapping("/client/restaurants/{restaurantId}/reviews")
    public String clientReviews(@PathVariable Long restaurantId, Model model) throws RemoteException
    {
        System.out.println("HOME_RESTAURANT");
        model.addAttribute("restaurant", proxyRestaurant.findById(restaurantId));

        if (proxyRestaurant.getComments(proxyRestaurant.findById(restaurantId)) != null)
            model.addAttribute("comments", proxyRestaurant.getComments(proxyRestaurant.findById(restaurantId)));

        return "client_reviews_client";
    }
}