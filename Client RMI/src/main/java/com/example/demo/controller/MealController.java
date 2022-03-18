package com.example.demo.controller;

import com.example.demo.model.Meal;
import com.example.demo.model.MealCategory;
import com.example.demo.model.MealForm;
import com.example.demo.rmi.inter.IMealCategoryRmiRemote;
import com.example.demo.rmi.inter.IMealRmiRemote;
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
public class MealController
{
    IRestaurantRmiRemote proxyRestaurant;
    IMealRmiRemote proxyMeal;
    IMealCategoryRmiRemote proxyMealCategory;

    MealController() throws RemoteException, NotBoundException, MalformedURLException
    {
        proxyRestaurant = (IRestaurantRmiRemote) Naming.lookup("rmi://localhost:1099/restaurant");
        proxyMeal = (IMealRmiRemote) Naming.lookup("rmi://localhost:1099/meal");
        proxyMealCategory = (IMealCategoryRmiRemote) Naming.lookup("rmi://localhost:1099/mealCategory");
    }

    MealCategory getOrCreateMealCategory(String name) throws RemoteException
    {
        if (proxyMealCategory.findByName(name) != null)
            return proxyMealCategory.findByName(name);

        return proxyMealCategory.newMealCategory(new MealCategory(null, name, null));
    }

    @GetMapping("/restaurants/{restaurantId}/meals/new")
    public String newMeal(MealForm mealForm, @PathVariable Long restaurantId, Model model)
    {
        System.out.println("NEW_FORM_MEAL");
//        model.addAttribute("mealForm", mealForm);
//        model.addAttribute("restaurantId", restaurantId);
        return "new_meal";
    }

    @PostMapping("/restaurants/{restaurantId}/meals/new")
    public String newMeal(@Valid MealForm mealForm, BindingResult bindingResult, @PathVariable Long restaurantId, Principal principal) throws RemoteException
    {
        System.out.println("NEW_MEAL");

        if (bindingResult.hasErrors())
        {
            return "new_meal";
        }

        Meal meal = new Meal(null, mealForm.getMeal(), mealForm.getPrice(), proxyRestaurant.findById(restaurantId), getOrCreateMealCategory(mealForm.getMealCategory()));

        System.out.println("SAVE_MEAL");

        proxyMeal.saveMeal(meal);

        System.out.println("REDIRECT_MEAL");

        return "redirect:/manager/restaurants/" + restaurantId;
    }

    @GetMapping("/meals/edit/{mealId}")
    public String edit(@PathVariable Long mealId, Model model) throws RemoteException
    {
        System.out.println("UPDATE_FORM_MEAL");

        Meal meal = proxyMeal.findById(mealId);
        MealForm mealForm = new MealForm(meal.getMeal(), meal.getPrice(), meal.getMealCategory().getCategory());

        model.addAttribute("mealForm", mealForm);
        model.addAttribute("mealId", mealId);
        return "update_meal";
    }

    @PostMapping("/meals/edit/{mealId}")
    public String edit(@Valid MealForm mealForm, BindingResult bindingResult, @PathVariable Long mealId, Principal principal) throws RemoteException
    {
        System.out.println("UPDATE_MEAL");

        if (bindingResult.hasErrors())
        {
            return "update_meal";
        }

        Meal meal = new Meal(mealId, mealForm.getMeal(), mealForm.getPrice(), proxyMeal.findById(mealId).getRestaurant(), getOrCreateMealCategory(mealForm.getMealCategory()));

        System.out.println("SAVE_MEAL");

        proxyMeal.saveMeal(meal);

        System.out.println("REDIRECT_MEAL");

        return "redirect:/manager/restaurants/" + proxyMeal.findById(mealId).getRestaurant().getId();
    }
}