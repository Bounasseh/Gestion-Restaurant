package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Client;
import com.example.demo.model.Restaurant;
import com.example.demo.model.RestaurantCategory;
import com.example.demo.rmi.inter.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

@Controller
public class SearchController
{
    IClientRmiRemote proxyClient;
    IRestaurantRmiRemote proxyRestaurant;
    IRestaurantCategoryRmiRemote proxyRestaurantCategory;
    ICityRmiRemote proxyCity;
    IFavoriteRestaurantRmiRemote proxyFavoriteRestaurant;

    public SearchController() throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyClient = (IClientRmiRemote) Naming.lookup("rmi://localhost:1099/client");
        proxyRestaurant = (IRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/restaurant");
        proxyRestaurantCategory = (IRestaurantCategoryRmiRemote) Naming.lookup("rmi://localhost:1099/restaurantCategory");
        proxyCity = (ICityRmiRemote) Naming.lookup("rmi://localhost:1099/city");
        proxyFavoriteRestaurant = (IFavoriteRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/favoriteRestaurant");
    }

    @GetMapping("/search")
    String search(@RequestParam(defaultValue = "") String input, @RequestParam(defaultValue = "restaurant") String filtre, Model model, Principal principal) throws RemoteException
    {
        System.out.println("HOME_CLIENT");
        Client client = proxyClient.findByUsername(principal.getName());
        model.addAttribute("client", client);
        model.addAttribute("input", input);
        model.addAttribute("filtre", filtre); // A ne pas supprimer
        boolean restaurantFilterIsSelected, categotyFilterIsSelected, cityFilterIsSelected;

        if (!input.isEmpty())
        {
            switch (filtre)
            {
                case "restaurant":
                {
                    System.out.println("CASE_RESTAURANT");
                    TreeMap<Restaurant, Boolean> restaurants = new TreeMap<>()
                    {
                        {
                            for (Restaurant restaurant : proxyRestaurant.findByNameLike(input))
                            {
                                put(restaurant, proxyFavoriteRestaurant.inFavoriteRestaurants(proxyClient.findByUsername(principal.getName()), restaurant));
                            }
                        }
                    };

                    System.out.println("RESTO");
                    model.addAttribute("restaurants", restaurants);
                    break;
                }
                case "category":
                {
                    System.out.println("CASE_CATEGORY");
                    TreeMap<Restaurant, Boolean> restaurants = new TreeMap<>();
                    Collection<RestaurantCategory> restaurantCategories = proxyRestaurantCategory.findByNameLike(input);
                    for (RestaurantCategory restaurantCategory : restaurantCategories)
                    {
                        for (Restaurant restaurant : proxyRestaurant.findByCategory(restaurantCategory))
                        {
                            restaurants.put(restaurant, proxyFavoriteRestaurant.inFavoriteRestaurants(proxyClient.findByUsername(principal.getName()), restaurant));
                        }
                    }
                    model.addAttribute("restaurants", restaurants);
                    break;
                }
                case "city":
                {
                    System.out.println("CASE_CITY");
                    TreeMap<Restaurant, Boolean> restaurants = new TreeMap<>();
                    Collection<City> cities = proxyCity.findByNameLike(input);
                    for (City city : cities)
                    {
                        for (Restaurant restaurant : proxyRestaurant.findByCity(city))
                        {
                            restaurants.put(restaurant, proxyFavoriteRestaurant.inFavoriteRestaurants(proxyClient.findByUsername(principal.getName()), restaurant));
                        }
                    }
                    model.addAttribute("restaurants", restaurants);
                    break;
                }
            }
        }
        else
        {
            System.out.println("INPUT = \"\"");
            TreeMap<Restaurant, Boolean> restaurants = new TreeMap<>()
            {
                {
                    for (Restaurant restaurant : proxyRestaurant.findAll())
                    {
                        put(restaurant, proxyFavoriteRestaurant.inFavoriteRestaurants(proxyClient.findByUsername(principal.getName()), restaurant));
                    }
                }
            };
            model.addAttribute("restaurants", restaurants);
        }

        restaurantFilterIsSelected = filtre.equals("restaurant");
        categotyFilterIsSelected = filtre.equals("category");
        cityFilterIsSelected = filtre.equals("city");

        model.addAttribute("restaurantFilterIsSelected", restaurantFilterIsSelected);
        model.addAttribute("categotyFilterIsSelected", categotyFilterIsSelected);
        model.addAttribute("cityFilterIsSelected", cityFilterIsSelected);

        return "search";
    }

    @PostMapping("/favrouriteRestaurant/add/{restaurantId}")
    String addFavoriteRestaurant(@PathVariable Long restaurantId, Principal principal, @RequestParam(defaultValue = "") String input, @RequestParam(defaultValue = "restaurant") String filtre) throws RemoteException
    {
        System.out.println("ADD_FAVORITE_RESTAURANT");
        proxyFavoriteRestaurant.addFavoriteRestaurant(proxyClient.findByUsername(principal.getName()), proxyRestaurant.findById(restaurantId));
        return "redirect:/search?input=" + input + "&filtre=" + filtre;
    }

    @PostMapping("/favrouriteRestaurant/remove/{restaurantId}")
    String removeFavoriteRestaurant(@PathVariable Long restaurantId, Principal principal, @RequestParam(defaultValue = "") String input, @RequestParam(defaultValue = "restaurant") String filtre) throws RemoteException
    {
        System.out.println("REMOVE_FAVORITE_RESTAURANT");
        proxyFavoriteRestaurant.removeFavoriteRestaurant(proxyFavoriteRestaurant.findByClientAndRestaurant(proxyClient.findByUsername(principal.getName()), proxyRestaurant.findById(restaurantId)));
        return "redirect:/search?input=" + input + "&filtre=" + filtre;
    }
}
