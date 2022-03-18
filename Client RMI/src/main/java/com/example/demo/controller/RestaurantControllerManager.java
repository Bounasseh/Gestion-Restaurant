package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantCategory;
import com.example.demo.model.RestaurantForm;
import com.example.demo.rmi.inter.ICityRmiRemote;
import com.example.demo.rmi.inter.IManagerRmiRemote;
import com.example.demo.rmi.inter.IRestaurantCategoryRmiRemote;
import com.example.demo.rmi.inter.IRestaurantRmiRemote;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Controller
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
public class RestaurantControllerManager
{
    IRestaurantRmiRemote proxyRestaurant;
    IManagerRmiRemote proxyManager;
    ICityRmiRemote proxyCity;
    IRestaurantCategoryRmiRemote proxyRestaurantCategory;

    RestaurantControllerManager() throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyRestaurant = (IRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/restaurant");
        proxyManager = (IManagerRmiRemote) Naming.lookup("rmi://localhost:1099/manager");
        proxyCity = (ICityRmiRemote) Naming.lookup("rmi://localhost:1099/city");
        proxyRestaurantCategory = (IRestaurantCategoryRmiRemote) Naming.lookup("rmi://localhost:1099/restaurantCategory");
    }

    City getOrCreateCity(String name) throws RemoteException
    {
        if (proxyCity.findByName(name) != null)
            return proxyCity.findByName(name);

        return proxyCity.newCity(new City(null, name, null));
    }

    RestaurantCategory getOrCreateRestaurantCategory(String name) throws RemoteException
    {
        if (proxyRestaurantCategory.findByName(name) != null)
            return proxyRestaurantCategory.findByName(name);

        return proxyRestaurantCategory.newRestaurantCategory(new RestaurantCategory(null, name, null));
    }

    @GetMapping("/manager/restaurants/{restaurantId}")
    public String homeRestaurantFromManager(@PathVariable Long restaurantId, Model model) throws RemoteException
    {
        System.out.println("HOME_RESTAURANT_FROM_MANAGER");
        model.addAttribute("restaurant", proxyRestaurant.findById(restaurantId));
        return "home_restaurant_manager";
    }

    @GetMapping("/manager/restaurants/{restaurantId}/reviews")
    public String clientReviews(@PathVariable Long restaurantId, Model model) throws RemoteException
    {
        System.out.println("HOME_RESTAURANT");
        model.addAttribute("restaurant", proxyRestaurant.findById(restaurantId));

        if (proxyRestaurant.getComments(proxyRestaurant.findById(restaurantId)) != null)
            model.addAttribute("comments", proxyRestaurant.getComments(proxyRestaurant.findById(restaurantId)));

        return "client_reviews_manager";
    }

    @GetMapping("/restaurants/new")
    public String newRestaurant(RestaurantForm restaurantForm)
    {
        System.out.println("NEW_FORM_RESTAURANT");
        return "new_restaurant";
    }

    @PostMapping("/restaurants/new")
    public String newRestaurant(@Valid RestaurantForm restaurantForm, BindingResult bindingResult, Principal principal) throws RemoteException
    {
        System.out.println("NEW_RESTAURANT");

        if (bindingResult.hasErrors())
        {
            return "new_restaurant";
        }

        Restaurant restaurant = new Restaurant(null, restaurantForm.getName(), restaurantForm.getDescription(), null, restaurantForm.getPhone(), null, null, getOrCreateCity(restaurantForm.getCity()), restaurantForm.getOpeningTime(), restaurantForm.getClosingTime(), getOrCreateRestaurantCategory(restaurantForm.getRestaurantCategory()), proxyManager.findByUsername(principal.getName()), null, null, null);

        System.out.println("SAVE_RESTAURANT");

        proxyRestaurant.saveRestaurant(restaurant);

        System.out.println("REDIRECT_RESTAURANT");

        return "redirect:/manager/home";
    }

    @GetMapping("/restaurants/edit/{restaurantId}")
    public String edit(@PathVariable Long restaurantId, Model model) throws RemoteException
    {
        System.out.println("UPDATE_FORM_RESTAURANT");

        Restaurant restaurant = proxyRestaurant.findById(restaurantId);
        RestaurantForm restaurantForm = new RestaurantForm(restaurant.getName(), restaurant.getDescription(), restaurant.getPhone(), restaurant.getCity().getCity(), restaurant.getOpeningTime(), restaurant.getClosingTime(), restaurant.getRestaurantCategory().getCategory());

        model.addAttribute("restaurantForm", restaurantForm);
        model.addAttribute("restaurantId", restaurantId);
        return "update_restaurant";
    }

    @PostMapping("/restaurants/edit/{restaurantId}")
    public String edit(@Valid RestaurantForm restaurantForm, BindingResult bindingResult, @PathVariable Long restaurantId, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_RESTAURANT");


        if (bindingResult.hasErrors())
        {
            return "update_restaurant";
        }

        Restaurant restaurant = new Restaurant(restaurantId, restaurantForm.getName(), restaurantForm.getDescription(), null, restaurantForm.getPhone(), null, null, getOrCreateCity(restaurantForm.getCity()), restaurantForm.getOpeningTime(), restaurantForm.getClosingTime(), getOrCreateRestaurantCategory(restaurantForm.getRestaurantCategory()), proxyManager.findByUsername(principal.getName()), proxyRestaurant.findById(restaurantId).getMeals(), proxyRestaurant.findById(restaurantId).getComments(), proxyRestaurant.findById(restaurantId).getFavoriteRestaurants());

        System.out.println("SAVE_RESTAURANT");

        proxyRestaurant.saveRestaurant(restaurant);

        System.out.println("REDIRECT_RESTAURANT");

        return "redirect:/manager/restaurants/" + restaurantId;
    }
}